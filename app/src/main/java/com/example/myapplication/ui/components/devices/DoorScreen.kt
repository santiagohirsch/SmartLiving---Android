package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.util.DevicesViewModels.DoorViewModel

@Preview
@Composable
fun Door(doorViewModel: DoorViewModel = viewModel()){
    var switchOpen by remember {
        mutableStateOf(false)
    }
    var switchLock by remember {
        mutableStateOf(false)
    }
    var openEnable by remember {
        mutableStateOf(true)
    }
    var lockEnable by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Switch(
                checked = switchOpen,
                onCheckedChange = { switchOn_ ->
                    switchOpen = switchOn_
                    lockEnable = !switchOn_
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Green,
                    checkedTrackColor = Color.Gray
                ),
                enabled = openEnable,
                modifier = Modifier.scale(2.2f)
            )
            Spacer(modifier = Modifier.width(23.dp))
            Text(
                text = if (switchOpen) "Open" else "Close",
                fontSize = 30.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Switch(
                checked = switchLock,
                onCheckedChange = { switchOn_ ->
                    switchLock = switchOn_
                    openEnable = !switchOn_
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Green,
                    checkedTrackColor = Color.Gray
                ),
                enabled = lockEnable,
                modifier = Modifier.scale(2.2f)
            )
            Spacer(modifier = Modifier.width(23.dp))
            Text(
                text = if (switchLock) "Locked" else "Unlocked",
                fontSize = 30.sp
            )
        }

    }
}