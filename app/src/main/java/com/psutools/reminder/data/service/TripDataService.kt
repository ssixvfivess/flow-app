package com.psutools.reminder.data.service;

import com.psutools.reminder.data.model.TripDataApi
import retrofit2.http.GET

interface TripDataService {

    @GET("/routes-service/trips")
    suspend fun getAllTrips(): List<TripDataApi>
}
