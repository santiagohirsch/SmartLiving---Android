package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.R
import com.example.myapplication.ui.theme.SmartLivingTheme
import com.example.myapplication.util.devicesrep.CurrentDevices
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.LampViewModel

@Composable
fun DeviceCard(
    device: DeviceViewModel,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(5.dp)
            .width(140.dp)
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
        ) {
            Image(
                painter = painterResource(device.img),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 6.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable { showDialog = true }
                    .size(112.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Allocate remaining space to the second element
            ) {
                Text(
                    text = device.name,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = modifier
                        .clip(RoundedCornerShape(5.dp))
                    //style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }

    if (showDialog) {
        CustomDialog(device = device, onDismiss = {showDialog = false})
    }
}

/*@Preview
@Composable
fun DeviceCardPreview() {
    val windowSizeClass = Material3WindowAmbient.windowSizeClass
    SmartLivingTheme(windowSizeClass = windowSizeClass) {
        DeviceCard(device = LampViewModel("el pepe"))
    }
}
*/
@Composable
fun CustomDialog(device: DeviceViewModel,onDismiss: ()-> Unit) {
    val currentDevices: CurrentDevices = CurrentDevices()
    Dialog(
        onDismissRequest = { onDismiss()},
        content = {
            Box(
                modifier = Modifier
                    .background(colorResource(R.color.primary_button))
                    .height(680.dp)
            ) {
                IconButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = "")
                }
                currentDevices.ViewDevice(device)
            }
        }
    )
}
