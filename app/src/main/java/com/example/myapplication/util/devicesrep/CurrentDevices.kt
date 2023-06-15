package com.example.myapplication.util.devicesrep

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.R
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

    @Composable
    fun ViewDevice(device: DeviceViewModel) {
        if(device.type == "ac")
            Ac(device as AcViewModel)
        else if(device.type == "lamp")
            Lamp(device as LampViewModel)
        else if(device.type == "refrigerator")
            Fridge(device as RefrigeratorViewModel)
        else if(device.type == "door")
            Door(device as DoorViewModel)
        else if(device.type == "vacuum")
            Vacuum(device as VacuumViewModel)
    }
}