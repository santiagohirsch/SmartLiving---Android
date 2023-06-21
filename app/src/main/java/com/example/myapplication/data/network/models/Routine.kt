package com.example.myapplication.data.network.models

import com.google.gson.annotations.SerializedName

data class Routine (

    @SerializedName("id"      ) var id      : String?            = null,
    @SerializedName("name"    ) var name    : String?            = null,
    @SerializedName("actions" ) var actions : List<Actions>      = listOf()

)