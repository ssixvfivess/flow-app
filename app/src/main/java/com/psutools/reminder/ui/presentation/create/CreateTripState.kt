package com.psutools.reminder.ui.presentation.create

import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.domain.model.trip.TransportData
import com.psutools.reminder.domain.model.trip.TripData
import org.joda.time.DateTime

sealed class CreateTripState {
    //начальное состояние (пустая форма)
    object Empty : CreateTripState()

    //данные формы заполнены, но поездка еще не создана
    data class FormFilled(
        val route: List<PointData>,
        val transportType: List<TransportData>,
        val overtime: Int,
        val arrivalDateTime: DateTime,
        val name: String,
        val isFormValid: Boolean // для активации/деактивации кнопки
    ) : CreateTripState()

    //процесс создания
    object Loading : CreateTripState()

    //поездка успешно создана
    data class Success(
        val tripId: String,
        val createdTrip: TripData? = null // опционально, если нужно показать детали
    ) : CreateTripState()

    //ошибка при создании
    data class Error(
        val errorMessage: String,
        val originalForm: FormFilled? = null // для возможности повтора
    ) : CreateTripState()
}