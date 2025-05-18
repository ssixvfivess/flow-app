//package com.psutools.reminder.ui.presentation.create
//
//import com.psutools.reminder.base.ResourceProvider
//import com.psutools.reminder.domain.model.creation.CreateTripRequest
//import javax.inject.Inject
//import org.joda.time.DateTime
//import org.joda.time.format.DateTimeFormat
//
//class CreateTripUiMapper @Inject constructor(
//   private val resourceProvider: ResourceProvider
//){
//    private val dateTimePattern = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")
//
//    // Преобразование UI состояния в Domain модель (для отправки на бэкенд)
//    fun mapUiToDomain(uiState: CreateTripState.FormFilled): CreateTripRequest {
//        return CreateTripRequest(
//            route = uiState.route,
//            transportType = uiState.transportType,
//            overtime = uiState.overtime,
//            arrivalDateTime = uiState.arrivalDateTime,
//            name = uiState.name
//        )
//    }
//
//    // TODO: нужно сделат так чтобы при создании точки n можно было бы ставить эти точки в центер между а и б
//
//    // Преобразование Domain модели в UI состояние (например, после ошибки)
//    fun mapDomainToUi(domainModel: CreateTripRequest): CreateTripState.FormFilled {
//        return CreateTripState.FormFilled(
//            route = domainModel.route,
//            transportType = domainModel.transportType,
//            overtime = domainModel.overtime,
//            arrivalDateTime = domainModel.arrivalDateTime,
//            name = domainModel.name,
//            isFormValid = validateForm(domainModel) // Проверяем валидность формы
//        )
//    }
//
//    // Валидация формы (можно вынести в отдельный класс)
//    private fun validateForm(domainModel: CreateTripRequest): Boolean {
//        return domainModel.name.isNotBlank() &&
//                domainModel.route.isNotEmpty() &&
//                domainModel.transportType.isNotEmpty()
//    }
//}
