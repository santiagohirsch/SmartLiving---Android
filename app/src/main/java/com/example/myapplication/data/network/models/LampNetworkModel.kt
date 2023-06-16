package com.example.myapplication.data.network.models

import com.example.myapplication.data.network.states.LampNetworkState
import com.google.gson.annotations.SerializedName

data class LampNetworkModel(
    @SerializedName("id"    ) override var id    : String? = null,
    @SerializedName("name"  ) override var name  : String? = null,
    @SerializedName("type"  ) override var type  : Type?   = Type(),
    @SerializedName("state" )  var state : LampNetworkState?  = LampNetworkState()
) : Device(id,name,type)

