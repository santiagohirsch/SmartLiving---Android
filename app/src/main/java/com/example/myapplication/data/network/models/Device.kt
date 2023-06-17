package com.example.myapplication.data.network.models

import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("id")  var id    : String? = null,
    @SerializedName("name")  var name  : String? = null,
    @SerializedName("type")  var type  : Type?   = Type(),
    @SerializedName("state") var state : State? = State(),
)