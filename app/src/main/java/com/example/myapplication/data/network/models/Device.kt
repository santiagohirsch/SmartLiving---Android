package com.example.myapplication.data.network.models

import com.google.gson.annotations.SerializedName

open class Device(
    @SerializedName("id") open var id    : String? = null,
    @SerializedName("name") open var name  : String? = null,
    @SerializedName("type") open var type  : Type?   = Type(),
)