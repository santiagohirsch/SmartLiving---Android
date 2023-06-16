package com.example.myapplication.data


import com.example.myapplication.data.network.models.DevicesList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/devices")
    suspend fun getDevices() : Response<DevicesList>

}