package com.psutools.reminder.data.mapper

import com.psutools.reminder.data.model.PointDataApi
import com.psutools.reminder.data.model.TripDataApi
import com.psutools.reminder.domain.model.PointData
import com.psutools.reminder.domain.model.TransportData
import com.psutools.reminder.domain.model.TripData
import java.util.UUID
import javax.inject.Inject

class TripDataMapper @Inject constructor() {

    private fun toDomain(apiModel: TripDataApi): TripData {
        return TripData(
            id = UUID.fromString(apiModel.id),
            route = mapPointListToDomain(apiModel.route),
            transportType = TransportData.fromValue(apiModel.transportType),
            overtime = apiModel.overtime
        )
    }

    fun toDomain(apiModel: List<TripDataApi>): List<TripData> {
        return apiModel.map { toDomain(it) }
    }

    private fun mapPointToDomain(apiModel: PointDataApi): PointData { //PointDataMapper
        return PointData(
            name = apiModel.name,
            latitude = apiModel.latitude,
            longitude = apiModel.longitude,
            stopTime = apiModel.stopTime
        )
    }

    private fun mapPointListToDomain(apiModel: List<PointDataApi>): List<PointData> { //PointDataMapper
        return apiModel.map { mapPointToDomain(it) }
    }
}
