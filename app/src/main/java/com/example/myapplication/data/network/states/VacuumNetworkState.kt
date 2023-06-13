package com.example.myapplication.data.network.states

import com.google.gson.annotations.SerializedName

data class VacuumNetworkState(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("mode")
    var mode: String? = null,
    @SerializedName("batteryLevel")
    var batteryLevel: Int? = null,
    @SerializedName("location")
    var location: String? = null,
)