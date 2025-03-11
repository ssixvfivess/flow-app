package com.psutools.reminder.ui.sample.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.domain.usecase.sample.GetSampleDataListUseCase
import com.psutools.reminder.utils.coroutines.tryLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SampleDataListViewModel @Inject constructor(
    private val getSampleDataListUseCase: GetSampleDataListUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val sampleDataListUiMapper: SampleDataListUiMapper,
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<SampleDataListState>> = MutableStateFlow(ScreenState.Loading)
    val state: Flow<ScreenState<SampleDataListState>> = _state.asStateFlow()

    val hasContent: Boolean
        get() = _state.value is ScreenState.Content

    fun loadData() {
        viewModelScope.tryLaunch(
            doOnLaunch = {
                val data = getSampleDataListUseCase.invoke(maxLength = MAX_SAMPLE_TEXT_LENGTH)
                val items = sampleDataListUiMapper.createListItems(data)

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
        const val TAG = "SampleDataListViewModel"
        const val MAX_SAMPLE_TEXT_LENGTH = 150
    }
}
