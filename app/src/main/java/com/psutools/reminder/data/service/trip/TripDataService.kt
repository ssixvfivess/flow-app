package com.psutools.reminder.data.service.trip

import com.psutools.reminder.data.model.details.GetTripDetailsResponseApi
import com.psutools.reminder.data.model.trip.GetTripsListResponseApi
import retrofit2.http.GET
import retrofit2.http.Path

interface TripDataService {

    @GET("/v1/routes-service/trips?userId=4cef84ba-a98a-4089-b6d8-bf0416ad2208")
    suspend fun getTripsList(): GetTripsListResponseApi

    @GET("/v1/routes-service/trips/68c0f6ce-7f1b-47dc-84aa-ea171412c02c")
    suspend fun getTrip(/*@Path("tripId") tripId: String*/): GetTripDetailsResponseApi
}
