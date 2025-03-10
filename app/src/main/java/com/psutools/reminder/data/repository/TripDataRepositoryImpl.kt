package com.psutools.reminder.data.repository

import com.psutools.reminder.data.mapper.TripDataMapper
import com.psutools.reminder.data.service.TripDataService
import javax.inject.Inject

class TripDataRepositoryImpl @Inject constructor(
    private val mapper: TripDataMapper,
    private val service: TripDataService
) {
}