package com.psutools.reminder.data.service;

import com.psutools.reminder.data.model.TripDataApi
import retrofit2.http.GET

interface TripDataService { //не забудь поставить реализацию этого сервиса в DataModule!!!

    @GET("/routes-service/trips")
    suspend fun getAllTrips(): List<TripDataApi>
}
