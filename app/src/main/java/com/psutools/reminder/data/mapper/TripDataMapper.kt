package com.psutools.reminder.data.mapper

import com.psutools.reminder.data.model.TripDataApi
import com.psutools.reminder.domain.model.TripData
import javax.inject.Inject

class TripDataMapper @Inject constructor() {

    fun toDomain(apiModel: TripDataApi): TripData {
        return TripData(
            id = apiModel.id,
            route = apiModel.route,
            transportType = apiModel.transportType,
            overtime = apiModel.overtime
        )
    }
}

