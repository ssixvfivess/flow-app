package com.psutools.reminder.ui.presentation.create

import android.content.Context
import android.content.Intent
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psutools.reminder.app.navigation.Router
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.domain.model.trip.TransportData
import com.psutools.reminder.domain.usecase.creation.CreateTripUseCase
import com.psutools.reminder.utils.coroutines.tryLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import org.joda.time.DateTime
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val createTripUseCase: CreateTripUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val uiMapper: CreateTripUiMapper,
    private val router: Router
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<CreateTripState>> = MutableStateFlow(
        ScreenState.Loading
    )
    val state: Flow<ScreenState<CreateTripState>> = _state.asStateFlow()

    private val _createState =
        MutableStateFlow<CreateTripSubmissionState>(CreateTripSubmissionState.Idle)
    val createState: Flow<CreateTripSubmissionState> = _createState.asStateFlow()

    val hasContent: Boolean
        get() = _state.value is ScreenState.Content

    init {
        loadInitialState()
    }

    private fun loadInitialState() {
        _state.value = ScreenState.Content(
            CreateTripState.Empty
        )
    }

    fun updateFormState(
        route: List<PointData> = emptyList(),
        transportType: List<TransportData> = emptyList(),
        overtime: Int = 0,
        arrivalDateTime: DateTime = DateTime.now(),
        name: String = ""
    ) {
        val currentState = _state.value
        if (currentState is ScreenState.Content) {
            _state.value = ScreenState.Content(
                CreateTripState.FormFilled(
                    route = route,
                    transportType = transportType,
                    overtime = overtime,
                    arrivalDateTime = arrivalDateTime,
                    name = name,
                    isFormValid = validateForm(route, transportType, name)
                )
            )
        }
    }

    fun createTrip(context: Context) {
        val currentState = _state.value
        if (currentState !is ScreenState.Content || currentState.data !is CreateTripState.FormFilled) return

        viewModelScope.tryLaunch(
            doOnLaunch = {
                _createState.value = CreateTripSubmissionState.Loading

                val domainRequest = uiMapper.mapUiToDomain(currentState.data)
                createTripUseCase(domainRequest)
                    .onSuccess {
                        _createState.value = CreateTripSubmissionState.Success
                        val tripId = "generated-id" // Заменить на реальный ID из ответа
                        _navigationIntent.emit(router.createRouteDetailsIntent(context, tripId))
                    }
                    .onFailure { exception ->
                        _createState.value = CreateTripSubmissionState.Error(exception.message)
                        Timber.tag(TAG).e(exception, "Create trip failed")
                    }
            },
            doOnError = { error ->
                _createState.value = CreateTripSubmissionState.Error(error.message)
                Timber.tag(TAG).e(error, "Create trip error")
            },
            coroutineContext = coroutineDispatchers.io()
        )
    }

    private fun validateForm(
        route: List<PointData>,
        transportType: List<TransportData>,
        name: String
    ): Boolean {
        return name.isNotBlank() &&
                route.isNotEmpty() &&
                transportType.isNotEmpty()
    }

    // Навигационные события
    private val _navigationIntent = MutableSharedFlow<Intent>()
    val navigationIntent: SharedFlow<Intent> = _navigationIntent.asSharedFlow()

    sealed class CreateTripSubmissionState {
        object Idle : CreateTripSubmissionState()
        object Loading : CreateTripSubmissionState()
        object Success : CreateTripSubmissionState()
        data class Error(val message: String?) : CreateTripSubmissionState()
    }

    private companion object {
        const val TAG = "CreateTripViewModel"
    }
}