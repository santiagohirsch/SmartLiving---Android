package com.example.myapplication.data.network.models

data class DeviceToAdd(
    val type: Type2,
    val name: String,
    val meta: Meta
)

data class Type2(
    val id: String
)

data class Meta(
    val key: String,
    val value: String
)
