package com.psutools.reminder.data.mapper.trip

import com.psutools.reminder.data.model.trip.PointDataApi
import com.psutools.reminder.data.model.trip.TripDataApi
import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.domain.model.trip.TransportData
import com.psutools.reminder.domain.model.trip.TripData
import com.psutools.reminder.domain.model.trip.TripStatusData
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.UUID
import javax.inject.Inject

class TripDataMapper @Inject constructor() {

    private val pattern = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")

    private fun toDomain(apiModel: TripDataApi): TripData {
        return TripData(
            id = UUID.fromString(apiModel.id),
            userId = UUID.fromString(apiModel.userId),
            status = TripStatusData.fromValueStatus(apiModel.status),
            name = apiModel.name,
            route = mapPointListToDomain(apiModel.route),
            transportType = TransportData.fromValuesTransport(apiModel.transportType),
            overtime = apiModel.overtime,
            displayRouteTimes = apiModel.displayRouteTimes,
            arrivalDateTime = DateTime.parse(apiModel.arrivalDateTime, pattern),
            departureDateTime = DateTime.parse(apiModel.departureDateTime, pattern),
            routeTimes = (apiModel.routeTimes)
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
            stopTime = apiModel.stopTime,
            address = apiModel.address
        )
    }

    private fun mapPointListToDomain(apiModel: List<PointDataApi>): List<PointData> { //PointDataMapper
        return apiModel.map { mapPointToDomain(it) }
    }
}
