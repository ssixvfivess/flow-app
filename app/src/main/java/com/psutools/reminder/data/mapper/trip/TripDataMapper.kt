package com.psutools.reminder.data.mapper.trip

import com.psutools.reminder.data.model.trip.PointDataApi
import com.psutools.reminder.data.model.trip.TripDataApi
import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.domain.model.trip.TransportData
import com.psutools.reminder.domain.model.trip.TripData
import com.psutools.reminder.domain.model.trip.TripStatusData
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.util.UUID
import javax.inject.Inject

class TripDataMapper @Inject constructor() {

    private fun toDomain(apiModel: TripDataApi): TripData {
        return TripData(
            id = UUID.fromString(apiModel.id),
            userId = UUID.fromString(apiModel.userId),
            status = TripStatusData.fromValueStatus(apiModel.status),
            route = mapPointListToDomain(apiModel.route),
            transportType = TransportData.fromValueTransport(apiModel.transportType),
            overtime = apiModel.overtime,
            arrivalDateTime = DateTime.parse(apiModel.arrivalDateTime, ISODateTimeFormat.dateTime()),
            departureDateTime = DateTime.parse(apiModel.departureDateTime, ISODateTimeFormat.dateTime())

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
