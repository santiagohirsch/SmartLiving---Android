package com.example.myapplication.data.network.states

import com.google.gson.annotations.SerializedName

data class LampNetworkState(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("color")
    var color: String? = null,
    @SerializedName("brightness")
    var brightness: Double? = 0.0
)