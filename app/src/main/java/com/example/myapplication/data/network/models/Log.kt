package com.example.myapplication.data.network.models

import com.google.gson.annotations.SerializedName

data class Log (
    @SerializedName("timestamp" ) var timestamp : String?        = null,
    @SerializedName("deviceId"  ) var deviceId  : String?        = null,
    @SerializedName("action"    ) var action    : String?        = null,
    @SerializedName("params"    ) var params    : List<Any>   = listOf(),
    @SerializedName("result"    ) var result    : Any?           = null
)