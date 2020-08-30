package com.example.mqttkotlina.model.detect.request

import com.google.gson.annotations.SerializedName

data class Androidbox(

    @field:SerializedName("device_id")
    val device_id: String? = null,

    @field:SerializedName("datetime")
    val datetime: String? = null

)