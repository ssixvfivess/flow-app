package com.psutools.reminder.ui.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.domain.usecase.details.GetTripDetailsUseCase
import com.psutools.reminder.utils.coroutines.tryLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class TripDataDetailsViewModel @Inject constructor(
    private val getTripDetailsUseCase: GetTripDetailsUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val tripDataDetailsUiMapper: TripDataDetailsUiMapper,
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<TripDataDetailsState>> = MutableStateFlow(
        ScreenState.Loading
    )
    val state: Flow<ScreenState<TripDataDetailsState>> = _state.asStateFlow()

    val hasContent: Boolean
        get() = _state.value is ScreenState.Content

    fun loadData() {
        viewModelScope.tryLaunch(
            doOnLaunch = {
                val data = getTripDetailsUseCase()
                val details = tripDataDetailsUiMapper.createListItem(data)
                _state.value = ScreenState.Content(
                    TripDataDetailsState(details)
                )
            },
            doOnError = { error ->
                Timber.tag(TAG).e(error, "An error occurred while loading data")
            },
            coroutineContext = coroutineDispatchers.io()
        )
    }

    private companion object {
        const val TAG = "TripDataDetailsViewModel"
    }
}
