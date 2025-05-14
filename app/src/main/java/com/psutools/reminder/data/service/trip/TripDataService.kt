package com.psutools.reminder.data.service.trip

import com.psutools.reminder.data.model.creation.CreateTripRequestApi
import com.psutools.reminder.data.model.creation.CreateTripResponse
import com.psutools.reminder.data.model.details.GetTripDetailsResponseApi
import com.psutools.reminder.data.model.trip.GetTripsListResponseApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TripDataService {

    //📌RETROFIT Интерфейс для похода в сеть. Выдает api-модельки
    @GET("/v1/routes-service/trips?userId=4cef84ba-a98a-4089-b6d8-bf0416ad2208")
    suspend fun getTripsList(): GetTripsListResponseApi

    @GET("/v1/routes-service/trips/{tripId}")
    suspend fun getTrip(@Path("tripId") tripId: String): GetTripDetailsResponseApi

    @DELETE("/v1/routes-service/trips/{tripId}")
    suspend fun deleteTrip(@Path("tripId") tripId: String): Response<Unit>

    @POST("/v1/routes-service/trips")
    suspend fun createTrip(@Body request: CreateTripRequestApi): CreateTripResponse
}
