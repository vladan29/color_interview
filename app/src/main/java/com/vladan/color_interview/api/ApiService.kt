package com.vladan.color_interview.api

import com.vladan.color_interview.model.ApiResponsePerson
import com.vladan.color_interview.model.ListIds
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by vladan on 8/28/2020
 */
interface ApiService {
    @get:GET("list")
    val idList: Call<ListIds?>?

    @GET("get/{id}")
    fun getPersonDetails(@Path("id") id: String?): Call<ApiResponsePerson?>?

    companion object {
        const val BASE_URL = "http://opn-interview-service.nn.r.appspot.com/"
    }
}