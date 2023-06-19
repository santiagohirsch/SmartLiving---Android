package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.myapplication.util.devicesvm.AcViewModel
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.DoorViewModel

@Preview
@Composable
fun Door(doorViewModel: DoorViewModel = viewModel()){
    val uiState by doorViewModel.uiState.collectAsState()
    var switchOpen by remember {
        mutableStateOf(uiState.state.status == "opened")
    }
    var switchLock by remember {
        mutableStateOf(uiState.state.lock == "locked")
    }
    var openEnable by remember {
        mutableStateOf(uiState.state.lock == "unlocked")
    }
    var lockEnable by remember {
        mutableStateOf(uiState.state.status == "closed")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switch(
            checked = switchOpen,
            onCheckedChange = { switchOn_ ->
                switchOpen = switchOn_
                lockEnable = !switchOn_
                if(uiState.state.status == "opened") {
                    doorViewModel.close(doorViewModel.id.toString())
                }
                else {
                    doorViewModel.open(doorViewModel.id.toString())
                }
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                checkedTrackColor = Color.Gray
            ),
            enabled = openEnable,
            modifier = Modifier.scale(2.2f)
        )
        Image(
            painter = if(uiState.state.status == "opened") painterResource(R.drawable.open_door) else painterResource(R.drawable.closedoor),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 12.dp, bottom = 6.dp)
                .clip(RoundedCornerShape(5.dp))
                .size(130.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(100.dp))
        Switch(
            checked = switchLock,
            onCheckedChange = { switchOn_ ->
                switchLock = switchOn_
                openEnable = !switchOn_
                if(uiState.state.lock == "locked") {
                    doorViewModel.unlock(doorViewModel.id.toString())
                }
                else {
                    doorViewModel.lock(doorViewModel.id.toString())
                }
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                checkedTrackColor = Color.Gray
            ),
            enabled = lockEnable,
            modifier = Modifier.scale(2.2f)
        )
        Image(
            painter = if(uiState.state.lock == "locked") painterResource(R.drawable.lock) else painterResource(R.drawable.unlock),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 12.dp, bottom = 6.dp)
                .clip(RoundedCornerShape(5.dp))
                .size(130.dp),
            contentScale = ContentScale.Crop
        )
    }
}