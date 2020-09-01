package com.vladan.color_interview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vladan on 8/28/2020
 */
public class ApiResponsePerson {
    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public Person data;

}





