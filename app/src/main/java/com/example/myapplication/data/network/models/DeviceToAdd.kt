package com.example.myapplication.data.network.models

data class DeviceToAdd(
    val meta: Meta,
    val name: String,
    val type: Type2
)

data class Type2(
    val id: String
)

data class Meta(
    val key: String = "",
    val value: String = ""
)
