package com.example.myapplication.data


import com.example.myapplication.data.network.models.Device
import com.example.myapplication.data.network.models.DeviceToAdd
import com.example.myapplication.data.network.models.DevicesList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Objects

interface ApiService {

    @GET("/api/devices")
    suspend fun getDevices() : Response<DevicesList>

    /*@PUT("/api/devices/{id}")
    suspend fun addDevice(@Path("id")id: String, @Query("name")name: String): Response<Device>/*/
     */
     */

    @POST("/api/devices")
    suspend fun addNewDevice(@Body device: DeviceToAdd) : Response<Device>
}