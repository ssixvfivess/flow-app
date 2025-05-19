package com.psutools.reminder.domain.repository.details

import com.psutools.reminder.domain.model.details.TripDetailsData


//📌Интерфейс в domain слое чтобы UC и VM зависели от абстракции
interface TripDetailsRepository {
    //📌Внешний айдишник поездки
    suspend fun getTripData(tripId: String): TripDetailsData

    suspend fun deleteTrip(tripId: String): Result<Unit>
}