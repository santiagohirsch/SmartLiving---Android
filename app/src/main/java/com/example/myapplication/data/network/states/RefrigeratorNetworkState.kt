package com.example.myapplication.data.network.states

import com.google.gson.annotations.SerializedName

data class RefrigeratorNetworkState(
    @SerializedName("mode")
    var mode: String? = null,
    @SerializedName("freezerTemperature")
    var freezerTemperature: Int? = null,
    @SerializedName("temperature")
    var temperature: Int? = null
)