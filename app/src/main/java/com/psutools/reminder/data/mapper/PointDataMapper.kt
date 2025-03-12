package com.psutools.reminder.data.mapper

import com.psutools.reminder.data.model.PointDataApi
import com.psutools.reminder.domain.model.PointData
import javax.inject.Inject

class PointDataMapper @Inject constructor() {

    fun toDomain(apiModel: PointDataApi): PointData {
        return PointData(
            name = apiModel.name,
            latitude = apiModel.latitude,
            longitude = apiModel.longitude,
            stopTime = apiModel.stopTime
        )
    }

    fun toDomain(apiModel: List<PointDataApi>): List<PointData> {
        return apiModel.map { toDomain(it) }
    }
}
