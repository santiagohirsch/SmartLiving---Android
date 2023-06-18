package com.example.myapplication.util.devicesrep

import androidx.compose.runtime.Composable
import com.example.myapplication.ui.components.devices.Ac
import com.example.myapplication.ui.components.devices.Door
import com.example.myapplication.ui.components.devices.Fridge
import com.example.myapplication.ui.components.devices.Lamp
import com.example.myapplication.ui.components.devices.Vacuum
import com.example.myapplication.util.devicesvm.AcViewModel
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.DoorViewModel
import com.example.myapplication.util.devicesvm.LampViewModel
import com.example.myapplication.util.devicesvm.RefrigeratorViewModel
import com.example.myapplication.util.devicesvm.VacuumViewModel

class CurrentDevices {
    /*var devices: List<DeviceViewModel> = listOf(
        LampViewModel("el pepe"),
        AcViewModel("AC Device"),
        RefrigeratorViewModel( "Refrigerator Device")
        // Agrega más elementos de DeviceViewModel según tus necesidades
    )*/


    @Composable
    fun ViewDevice(device: DeviceViewModel) {
        when(device.type) {
            "ac" -> Ac(acViewModel = device as AcViewModel)
            "lamp" -> Lamp(lampViewModel = device as LampViewModel)
            "refrigerator" -> Fridge(fridgeViewModel = device as RefrigeratorViewModel)
            "door" -> Door(doorViewModel = device as DoorViewModel)
            "vacuum" -> Vacuum(vacuumViewModel = device as VacuumViewModel)
            else -> throw IllegalArgumentException("Unknown device type")
        }
    }
}