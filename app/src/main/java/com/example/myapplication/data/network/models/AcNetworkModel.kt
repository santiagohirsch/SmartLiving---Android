package com.example.myapplication.data.network.models

import com.example.myapplication.data.network.states.AcNetworkState
import com.google.gson.annotations.SerializedName

data class AcNetworkModel (
    @SerializedName("id"    ) override var id    : String? = null,
    @SerializedName("name"  ) override var name  : String? = null,
    @SerializedName("type"  ) override var type  : Type?   = Type(),
    @SerializedName("state" ) var state : AcNetworkState?  = AcNetworkState()
) : Device(id,name,type)
