package com.example.myapplication.data.network.states

import com.google.gson.annotations.SerializedName

data class AcNetworkState(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("temperature")
    var temperature: Int? = null,
    @SerializedName("mode")
    var mode: String? = null,
    @SerializedName("verticalSwing")
    var verticalSwing: String? = null,
    @SerializedName("horizontalSwing")
    var horizontalSwing: String? = null,
    @SerializedName("fanSpeed")
    var fanSpeed: String? = null,
)