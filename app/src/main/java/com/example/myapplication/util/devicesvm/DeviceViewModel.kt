package com.example.myapplication.util.devicesvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class DeviceViewModel(var type: String, val img: Int, var name: String?, var id: String?) : ViewModel() {

}
