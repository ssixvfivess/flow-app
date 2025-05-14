package com.psutools.reminder.data.repository.details

import com.psutools.reminder.data.mapper.details.TripDetailsMapper
import com.psutools.reminder.data.service.trip.TripDataService
import com.psutools.reminder.domain.model.details.TripDetailsData
import com.psutools.reminder.domain.repository.details.TripDetailsRepository
import javax.inject.Inject

//📌Управляет данными из внешних источников. Решает, откуда взять данные и куда отправить/сохранить. Дергает сервисы (api) и ДАО(bd) Выдает доменные модели.
class TripDetailsRepositoryImpl @Inject constructor(
    private val mapper: TripDetailsMapper,
    private val service: TripDataService
) : TripDetailsRepository {

    override suspend fun getTripData(tripId: String): TripDetailsData {
        val apiTrip = service.getTrip(tripId) //📌Запрос к API. обращается к сетевому сервису (Retrofit)
        return mapper.toDomain(apiTrip.data) //📌Конвертация в доменную модель. преобразует DTO (Data Transfer Object) в доменную модель
    }

    override suspend fun deleteTrip(tripId: String): Result<Unit> {
        return try {
            val response = service.deleteTrip(tripId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Server error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}