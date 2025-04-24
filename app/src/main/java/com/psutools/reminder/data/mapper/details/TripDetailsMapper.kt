package com.psutools.reminder.data.mapper.details

import com.psutools.reminder.data.model.details.TripDetailsDataApi
import com.psutools.reminder.data.model.trip.PointDataApi
import com.psutools.reminder.domain.model.details.TripDetailsData
import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.domain.model.trip.TransportData
import com.psutools.reminder.domain.model.trip.TripStatusData
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.UUID
import javax.inject.Inject

class TripDetailsMapper @Inject constructor() {

    private val pattern = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")

    fun toDomain(apiModel: TripDetailsDataApi): TripDetailsData {
        return TripDetailsData(
            id = UUID.fromString(apiModel.id),
            userId = UUID.fromString(apiModel.userId),
            status = TripStatusData.fromValueStatus(apiModel.status),
            name = apiModel.name,
            route = mapPointListToDomain(apiModel.route),
            transportType = TransportData.fromValuesTransport(apiModel.transportType),
            overtime = apiModel.overtime,
            arrivalDateTime = DateTime.parse(apiModel.arrivalDateTime, pattern),
            departureDateTime = DateTime.parse(apiModel.departureDateTime, pattern),
            routeTimes = apiModel.routeTimes
        )
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