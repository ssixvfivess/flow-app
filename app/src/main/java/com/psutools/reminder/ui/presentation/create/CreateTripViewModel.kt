package com.psutools.reminder.ui.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.utils.coroutines.tryLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val coroutineDispatchers: CoroutineDispatchers,
    private val uiMapper: CreateUiMapper
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<CreateTripState>> = MutableStateFlow(
        ScreenState.Loading
    )
    val state: StateFlow<ScreenState<CreateTripState>> = _state.asStateFlow()

    val hasContent: Boolean
        get() = _state.value is ScreenState.Content

    fun loadData() {
        viewModelScope.tryLaunch(
            doOnLaunch = {
                _state.value = ScreenState.Content(CreateTripState.initial())
            },
            doOnError = { error ->
                Timber.tag(TAG).e(error, "Error: ViewModel loadData()")
            },
            coroutineContext = coroutineDispatchers.io()
        )
    }

    fun updateSelectedTime(selectedTime: String) {
        updateState { currentState ->
            val updatedItems = uiMapper.mapTime(currentState.items, selectedTime)
            currentState.copy(items = updatedItems)
        }
    }

    fun updateSelectedDate(selectedDate: String) {
        updateState { currentState ->
            val updatedItems = uiMapper.mapDate(currentState.items, selectedDate)
            currentState.copy(items = updatedItems)
        }
    }

    fun updateSelectedReminder(selectedReminder: String) {
        updateState { currentState ->
            val updateItems = uiMapper.mapReminder(currentState.items, selectedReminder)
            currentState.copy(items = updateItems)
        }
    }

    private fun updateState(transform: (CreateTripState) -> CreateTripState) {
        val currentState = _state.value as? ScreenState.Content<CreateTripState>
        currentState?.let { contentState ->
            _state.value = ScreenState.Content(transform(contentState.data))
        }
    }

    companion object {
        const val TAG = "CreateTripViewModel"
    }
}