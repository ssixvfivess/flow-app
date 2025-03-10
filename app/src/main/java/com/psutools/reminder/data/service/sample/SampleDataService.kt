package com.psutools.reminder.data.service.sample

import com.psutools.reminder.data.model.sample.SampleDataApi
import com.psutools.reminder.data.model.sample.SampleDataListApi
import retrofit2.http.GET
import retrofit2.http.Query

interface SampleDataService {

    @GET("/fact")
    suspend fun getFact(
        @Query("max_length") maxLength: Int
    ): SampleDataApi

    @GET("/facts")
    suspend fun getFactsList(
        @Query("max_length") maxLength: Int
    ): SampleDataListApi
}
