package com.psutools.reminder.domain.usecase.details

import com.psutools.reminder.domain.model.details.TripDetailsData
import com.psutools.reminder.domain.model.trip.TripData
import com.psutools.reminder.domain.repository.details.TripDetailsRepository
import javax.inject.Inject

//📌interface — определяет контракт для всех реализаций UseCase
interface GetTripDetailsUseCase {
    //📌suspend — указывает, что функция корутина-дружелюбна (может выполняться в корутине)
    //📌operator fun invoke — позволяет вызывать объект UseCase как функцию (синтаксический сахар)
    //📌tripId: String — входной параметр (идентификатор поездки)
    //📌TripDetailsData — возвращаемая доменная модель данных о поездке
    suspend operator fun invoke(tripId: String): TripDetailsData
}

//📌@Inject constructor — пометка для Hilt, что этот класс нужно внедрять через DI
class GetTripDetailsUseCaseImpl @Inject constructor(
    //📌repository: TripDetailsRepository — зависимость от репозитория (источника данных)
    private val repository: TripDetailsRepository,
    //📌: GetTripDetailsUseCase — реализация интерфейса UseCase
) : GetTripDetailsUseCase {

    //📌repository.getTripData(tripId) — делегирует запрос данных репозиторию
    override suspend fun invoke(tripId: String): TripDetailsData {
        //📌Возвращает TripDetailsData — доменную модель (не DTO или Entity!).
        return repository.getTripData(tripId)
    }
}