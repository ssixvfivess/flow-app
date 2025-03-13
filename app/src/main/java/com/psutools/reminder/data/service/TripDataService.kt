package com.psutools.reminder.data.service

import com.psutools.reminder.data.model.GetTripsListResponseApi
import retrofit2.http.GET

interface TripDataService {

    @GET("/routes-service/trips")
    suspend fun getTripsList(): GetTripsListResponseApi

}
