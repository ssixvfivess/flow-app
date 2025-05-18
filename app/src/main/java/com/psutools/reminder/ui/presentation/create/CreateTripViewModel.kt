package com.psutools.reminder.ui.presentation.create

import androidx.lifecycle.ViewModel
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.domain.model.trip.TransportData
import com.psutools.reminder.domain.usecase.creation.CreateTripUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.joda.time.DateTime
import javax.inject.Inject


@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val createTripUseCase: CreateTripUseCase,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {
    private val _state: MutableStateFlow<ScreenState<CreateTripState>> =
        MutableStateFlow(ScreenState.Loading)
    val state: Flow<ScreenState<CreateTripState>> = _state.asStateFlow()

    val hasContent: Boolean
        get() = _state.value is ScreenState.Content

    private fun loadInitialState() {
        _state.value = ScreenState.Content(CreateTripState.Empty)
    }

    init {
        loadInitialState()
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

    private fun validateForm(
        route: List<PointData>,
        transportType: List<TransportData>,
        name: String
    ): Boolean {
        return name.isNotBlank() &&
                route.isNotEmpty() &&
                transportType.isNotEmpty()
    }

    sealed class CreateTripSubmissionState {
        object Idle : CreateTripSubmissionState()
        object Loading : CreateTripSubmissionState()
        object Success : CreateTripSubmissionState()
        data class Error(val message: String?) : CreateTripSubmissionState()
    }

    sealed class NavigationEvent {
        data class NavigateToDetails(val tripId: String) : NavigationEvent()
    }
}