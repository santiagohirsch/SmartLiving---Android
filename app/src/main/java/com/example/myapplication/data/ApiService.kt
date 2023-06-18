package com.example.myapplication.data


import com.example.myapplication.data.network.models.Device
import com.example.myapplication.data.network.models.DeviceToAdd
import com.example.myapplication.data.network.models.DevicesList
import com.example.myapplication.data.network.models.Params
import com.example.myapplication.data.network.models.RoutineList
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

    @GET("/api/routines")
    suspend fun getRoutines() : Response<RoutineList>

    @PUT("/api/devices/{deviceId}/{actionName}")
    suspend fun executePS(@Path("deviceId")deviceId: String, @Path("actionName")actionName: String, @Body params: List<String> )

    @PUT("/api/devices/{deviceId}/{actionName}")
    suspend fun executePI(@Path("deviceId")deviceId: String, @Path("actionName")actionName: String, @Body params: List<Int> )

    @PUT("/api/devices/{deviceId}/{actionName}")
    suspend fun execute(@Path("deviceId")deviceId: String, @Path("actionName")actionName: String )

    @PUT("/api/routines/{routineId}/execute")
    suspend fun executeRoutine(@Path("routineId")routineId: String)
}