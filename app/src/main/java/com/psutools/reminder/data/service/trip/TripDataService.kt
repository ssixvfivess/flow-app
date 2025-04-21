package com.psutools.reminder.data.service.trip

import com.psutools.reminder.data.model.details.GetTripDetailsResponseApi
import com.psutools.reminder.data.model.trip.GetTripsListResponseApi
import retrofit2.http.GET

interface TripDataService {

    @GET("/v2/routes-service/trips")
    suspend fun getTripsList(): GetTripsListResponseApi

    suspend fun getTrip(): GetTripDetailsResponseApi

}
