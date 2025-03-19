package com.psutools.reminder.ui.sample.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.domain.usecase.sample.GetSampleDataUseCase
import com.psutools.reminder.ui.sample.details.sample.SampleDataDetailsState
import com.psutools.reminder.ui.sample.details.sample.SampleDataDetailsUiMapper
import com.psutools.reminder.utils.coroutines.tryLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class TripDataDetailsViewModel @Inject constructor(
    private val getSampleDataUseCase: GetSampleDataUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val sampleDataDetailsUiMapper: TripDataDetailsUiMapper,
) : ViewModel() {
}
