package com.psutools.reminder.data.service.trip

import com.psutools.reminder.data.model.trip.GetTripsListResponseApi
import retrofit2.http.GET

interface TripDataService {

    @GET("/v1/routes-service/trips")
    suspend fun getTripsList(): GetTripsListResponseApi

}
