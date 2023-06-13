package com.example.myapplication.data.network.models

import com.example.myapplication.data.network.states.RefrigeratorNetworkState
import com.google.gson.annotations.SerializedName

data class RefrigeratorNetworkModel(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("type")
    var type: DeviceTypeNetworkModel,
    @SerializedName("state")
    var state: RefrigeratorNetworkState,
    @SerializedName("img")
    var img: Int? = null
)