package com.psutools.reminder.data.mapper.creation

import com.psutools.reminder.data.model.creation.CreateTripRequestApi
import com.psutools.reminder.data.model.trip.PointDataApi
import com.psutools.reminder.domain.model.creation.CreateTripRequest
import com.psutools.reminder.domain.model.trip.PointData
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class CreateTripMapper @Inject constructor() {
    private val pattern = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")

    fun toApi(domainModel: CreateTripRequest): CreateTripRequestApi {
        return CreateTripRequestApi(
            route = mapPointListToApi(domainModel.route),
            transportType = domainModel.transportType.map { it.name },
            overtime = domainModel.overtime,
            arrivalDateTime = pattern.print(domainModel.arrivalDateTime),
            name = domainModel.name
        )
    }

    private fun mapPointToApi(domainModel: PointData): PointDataApi {
        return PointDataApi(
            name = domainModel.name,
            latitude = domainModel.latitude,
            longitude = domainModel.longitude,
            stopTime = domainModel.stopTime,
            address = domainModel.address
        )
    }

    private fun mapPointListToApi(domainModel: List<PointData>): List<PointDataApi> {
        return domainModel.map { mapPointToApi(it) }
    }
}