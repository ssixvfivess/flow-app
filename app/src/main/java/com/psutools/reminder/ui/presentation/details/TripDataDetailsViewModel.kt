package com.psutools.reminder.ui.presentation.details

import androidx.lifecycle.ViewModel
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.domain.usecase.sample.GetSampleDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TripDataDetailsViewModel @Inject constructor(
    private val getSampleDataUseCase: GetSampleDataUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val sampleDataDetailsUiMapper: TripDataDetailsUiMapper,
) : ViewModel() {
}
