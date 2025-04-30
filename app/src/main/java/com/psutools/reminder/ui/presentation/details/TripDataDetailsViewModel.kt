package com.psutools.reminder.ui.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.domain.usecase.details.DeleteTripUseCase
import com.psutools.reminder.domain.usecase.details.GetTripDetailsUseCase
import com.psutools.reminder.utils.coroutines.tryLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class TripDataDetailsViewModel @Inject constructor(
    private val deleteTripUseCase: DeleteTripUseCase,
    private val getTripDetailsUseCase: GetTripDetailsUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val tripDataDetailsUiMapper: TripDataDetailsUiMapper,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<TripDataDetailsState>> = MutableStateFlow(
        ScreenState.Loading
    )
    val state: Flow<ScreenState<TripDataDetailsState>> = _state.asStateFlow()

    val hasContent: Boolean
        get() = _state.value is ScreenState.Content

    fun loadData() {
        val tripId = savedStateHandle.get<String>("tripId") ?: ""
        viewModelScope.tryLaunch(
            doOnLaunch = {
                val data = getTripDetailsUseCase(tripId)
                val details = tripDataDetailsUiMapper.createListItem(data)
                _state.value = ScreenState.Content(
                    TripDataDetailsState(
                        items = details,
                        routeName = data.name
                    )
                )
            },
            doOnError = { error ->
                Timber.tag(TAG).e(error, "An error occurred while loading data")
            },
            coroutineContext = coroutineDispatchers.io()
        )
    }

    private val _deleteState = MutableStateFlow<DeleteTripState>(DeleteTripState.Idle)

    fun deleteTrip(tripId: String) {
        viewModelScope.tryLaunch(
            doOnLaunch = {
                _deleteState.value = DeleteTripState.Loading

                deleteTripUseCase(tripId)
                    .onSuccess {
                        _state.value = ScreenState.Content(
                            TripDataDetailsState(
                                items = emptyList(),
                                routeName = ""
                            )
                        )
                        _deleteState.value = DeleteTripState.Success
                    }
                    .onFailure { exception ->
                        _deleteState.value = DeleteTripState.Error(exception.message)
                    }
            },
            doOnError = { error ->
                _deleteState.value = DeleteTripState.Error(error.message)
                Timber.tag(TAG).e(error, "Delete trip failed")
            },
            coroutineContext = coroutineDispatchers.io()
        )
    }

    private companion object {
        const val TAG = "TripDataDetailsViewModel"
    }

    sealed class DeleteTripState {
        object Idle : DeleteTripState()
        object Loading : DeleteTripState()
        object Success : DeleteTripState()
        data class Error(val message: String?) : DeleteTripState()
    }
}
