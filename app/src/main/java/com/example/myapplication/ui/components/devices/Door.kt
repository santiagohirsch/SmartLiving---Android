package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Door(){
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
    Column() {

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
                enabled = openEnable
            )
            Text(text = if (switchOpen) "Open" else "Close")
        }
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
                enabled = lockEnable
            )
            Text(text = if (switchLock) "Locked" else "Unlocked")
        }

    }
}