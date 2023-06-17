package com.example.myapplication.data.network.models

import com.google.gson.annotations.SerializedName

data class State (

    @SerializedName("freezerTemperature" ) var freezerTemperature : Int?    = null,
    @SerializedName("temperature"        ) var temperature        : Int?    = null,
    @SerializedName("verticalSwing"      ) var verticalSwing      : String? = null,
    @SerializedName("horizontalSwing"    ) var horizontalSwing    : String? = null,
    @SerializedName("fanSpeed"           ) var fanSpeed           : String? = null,
    @SerializedName("status"             ) var status             : String? = null,
    @SerializedName("lock"               ) var lock               : String? = null,
    @SerializedName("mode"               ) var mode               : String? = null,
    @SerializedName("batteryLevel"       ) var batteryLevel       : Int?    = null,
    @SerializedName("location"           ) var location           : String? = null,
    @SerializedName("color"              ) var color              : String? = null,
    @SerializedName("brightness"         ) var brightness         : Int?    = null

)