package com.psutools.reminder.ui.sample.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.domain.usecase.GetTripDataListUseCase
import com.psutools.reminder.utils.coroutines.tryLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TripDataListViewModel @Inject constructor(
    private val getTripDataListUseCase: GetTripDataListUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val tripDataListUiMapper: TripDataListUiMapper,
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<SampleDataListState>> = MutableStateFlow(ScreenState.Loading)
    val state: Flow<ScreenState<SampleDataListState>> = _state.asStateFlow()

    val hasContent: Boolean
        get() = _state.value is ScreenState.Content

    fun loadData() {
        viewModelScope.tryLaunch(
            doOnLaunch = {
                val data = getTripDataListUseCase()
                val items = tripDataListUiMapper.createListItems(data)

                _state.value = ScreenState.Content(
                    SampleDataListState(items = items)
                )
            },
            doOnError = { error ->
                Timber.tag(TAG).e(error, "An error occurred while loading data")
            },
            coroutineContext = coroutineDispatchers.io()
        )
    }

    private companion object {
        const val TAG = "TripDataListViewModel"
    }
}
