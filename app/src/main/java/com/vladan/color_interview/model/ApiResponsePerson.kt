package com.vladan.color_interview.model

import com.google.gson.annotations.SerializedName

/**
 * Created by vladan on 8/28/2020
 */
class ApiResponsePerson {
    @SerializedName("status")
    var status: String? = null

    @JvmField
    @SerializedName("data")
    var data: Person? = null
}