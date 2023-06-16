package com.example.myapplication.data.network.models

import com.example.myapplication.data.network.states.LampNetworkState
import com.example.myapplication.data.network.states.VacuumNetworkState
import com.google.gson.annotations.SerializedName

data class VacuumNetworkModel(
    @SerializedName("id"    ) override var id    : String? = null,
    @SerializedName("name"  ) override var name  : String? = null,
    @SerializedName("type"  ) override var type  : Type?   = Type(),
    @SerializedName("state" ) var state : VacuumNetworkState?  =VacuumNetworkState()
) : Device(id,name,type)