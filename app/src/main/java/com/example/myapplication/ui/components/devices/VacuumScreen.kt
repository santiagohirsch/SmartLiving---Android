package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.VacuumViewModel

@Preview
@Composable
fun Vacuum(vacuumViewModel: DeviceViewModel = viewModel()){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.aspiradora), contentDescription = "vacuum",
            modifier = Modifier
                .size(100.dp)
                .padding(top = 40.dp)
        )
        Button(
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = stringResource(R.string.select_mode_msg))
        }
        Button(
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = stringResource(R.string.change_location_msg))
        }
        Button(
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = stringResource(R.string.return_base_msg))
        }
    }
}