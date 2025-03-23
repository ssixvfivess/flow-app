package com.psutools.reminder.ui.sample.details.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.domain.usecase.sample.GetSampleDataUseCase
import com.psutools.reminder.utils.coroutines.tryLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SampleDataDetailsViewModel @Inject constructor(
    private val getSampleDataUseCase: GetSampleDataUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val sampleDataDetailsUiMapper: SampleDataDetailsUiMapper,
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<SampleDataDetailsState>> = MutableStateFlow(ScreenState.Loading)
    val state: Flow<ScreenState<SampleDataDetailsState>> = _state.asStateFlow()

    val hasContent: Boolean
        get() = _state.value is ScreenState.Content

    fun loadData() {
        viewModelScope.tryLaunch(
            doOnLaunch = {
                val data = getSampleDataUseCase.invoke(maxLength = MAX_SAMPLE_TEXT_LENGTH)
                val details = sampleDataDetailsUiMapper.createDetails(data)

                _state.value = ScreenState.Content(
                    SampleDataDetailsState(text = details)
                )
            },
            doOnError = { error ->
                Timber.tag(TAG).e(error, "An error occurred while loading data")
            },
            coroutineContext = coroutineDispatchers.io()
        )
    }

    private companion object {
        const val TAG = "SampleDataDetailsViewModel"
        const val MAX_SAMPLE_TEXT_LENGTH = 150
    }
}
