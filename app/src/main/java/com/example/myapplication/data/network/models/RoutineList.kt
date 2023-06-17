package com.example.myapplication.data.network.models

import com.google.gson.annotations.SerializedName

data class RoutineList (

    @SerializedName("result" ) var routines : List<Routine> = listOf()

)