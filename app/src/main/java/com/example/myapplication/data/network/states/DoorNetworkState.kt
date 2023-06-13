package com.example.myapplication.data.network.states

import com.google.gson.annotations.SerializedName

data class DoorNetworkState(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("lock")
    var lock: String? = null
)