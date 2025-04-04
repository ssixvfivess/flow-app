package com.psutools.reminder.ui.presentation.routes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.domain.model.trip.TripData
import com.psutools.reminder.domain.usecase.trip.GetTripDataListUseCase
import com.psutools.reminder.utils.coroutines.tryLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.joda.time.DateTime
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RoutesViewModel @Inject constructor(
    private val getRoutesDataListUseCase: GetTripDataListUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val routesDataListUiMapper: RoutesDataListUiMapper,
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<RoutesDataListState>> =
        MutableStateFlow(ScreenState.Loading)
    val state: Flow<ScreenState<RoutesDataListState>> = _state.asStateFlow()

    val hasContent: Boolean
        get() = _state.value is ScreenState.Content

    private var allTrips: List<TripData> = emptyList()
    private var selectedDate: DateTime? = null

    fun loadData() {
        viewModelScope.tryLaunch(doOnLaunch = {
            allTrips = getRoutesDataListUseCase()
            filterAndMapTrips()
        }, doOnError = { error ->
            Timber.tag(TAG).e(error, "An error occurred while loading data")
        }, coroutineContext = coroutineDispatchers.io()
        )
    }

    fun setSelectedDate(date: DateTime) {
        selectedDate = date
        filterAndMapTrips()
    }

    private fun filterAndMapTrips() {
        val filteredTrips = if (selectedDate != null) {
            allTrips.filter { trip ->
                trip.arrivalDateTime.withTimeAtStartOfDay() == selectedDate?.withTimeAtStartOfDay()
            }
        } else {
            allTrips
        }

        val items = routesDataListUiMapper.createListItems(filteredTrips)
        _state.value = ScreenState.Content(RoutesDataListState(items = items))
    }

    private companion object {
        const val TAG = "RoutesViewModel"
    }
}