package com.psutools.reminder.ui.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.utils.coroutines.tryLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<CreateTripState>> = MutableStateFlow(
        ScreenState.Loading
    )
    val state: Flow<ScreenState<CreateTripState>> = _state.asStateFlow()

    val hasContent: Boolean
        get() = _state.value is ScreenState.Content

    fun loadData() {
        viewModelScope.tryLaunch(
            doOnLaunch = {
                _state.value = ScreenState.Content(
                    CreateTripState(
                        routeAHint = "Выберите точку A",
                        routeBHint = "Выберите точку B",
                        pointA = "",
                        pointB = "",

                        selectedDate = "",
                        selectedTime = "",
                        selectedReminder = ""
                    )
                )
            },
            doOnError = { error ->
                Timber.tag(TAG).e(error, "Error: ViewModel loadData()")
            },
            coroutineContext = coroutineDispatchers.io()
        )
    }


    private companion object {
        const val TAG = "CreateTripViewModel"
    }

    fun updateSelectedTime(selectedTime: String) {
        val currentState = _state.value as? ScreenState.Content<CreateTripState>
        currentState?.data?.let { currentData ->
            _state.value = ScreenState.Content(
                currentData.copy(selectedTime = selectedTime)
            )
        }
    }

    fun updateSelectedDate(selectedDate: String) {
        updateState { it.copy(selectedDate = selectedDate) }
    }

    fun updateSelectedReminder(reminder: String) {
        updateState { it.copy(selectedReminder = reminder) }
    }

    private fun updateState(transform: (CreateTripState) -> CreateTripState) {
        val currentState = _state.value as? ScreenState.Content<CreateTripState>
        currentState?.data?.let { currentData ->
            _state.value = ScreenState.Content(transform(currentData))
        }
    }
}