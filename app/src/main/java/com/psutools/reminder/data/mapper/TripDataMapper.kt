package com.psutools.reminder.data.mapper

import com.psutools.reminder.data.model.GetTripsListResponseApi
import com.psutools.reminder.data.model.TripDataApi
import com.psutools.reminder.domain.model.TransportData
import com.psutools.reminder.domain.model.TripData
import java.util.UUID
import javax.inject.Inject

class TripDataMapper @Inject constructor() {
    private val pointDataMapper = PointDataMapper()

    fun toDomain(apiModel: TripDataApi): TripData {
        return TripData(
            id = UUID.fromString(apiModel.id),
            route = pointDataMapper.toDomain(apiModel.route),
            transportType = TransportData.fromValue(apiModel.transportType),
            overtime = apiModel.overtime
        )
    }

    fun toDomain(apiModel: GetTripsListResponseApi): List<TripData> {
        return apiModel.data.map { toDomain(it) }
    }
}
