package com.vladan.color_interview.api;

import com.vladan.color_interview.model.ListIds;
import com.vladan.color_interview.model.ApiResponsePerson;
import com.vladan.color_interview.model.Person;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by vladan on 8/28/2020
 */
public interface ApiService {
    String BASE_URL = "http://opn-interview-service.nn.r.appspot.com/";

    @GET("list")
    Call<ListIds> getIdList();

    @GET("get/{id}")
    Call<ApiResponsePerson> getPersonDetails(@Path("id") String id);
}
