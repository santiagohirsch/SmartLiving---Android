package com.example.myapplication.data.network.models


import com.google.gson.annotations.SerializedName


data class DevicesList (
    @SerializedName("result") var devices : List<Device> = listOf()

)
