import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import com.example.myapplication.R
import com.example.myapplication.ui.components.DeviceCard
import com.example.myapplication.util.devicesrep.CurrentDevices

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun DevicesScreen() {
    val currentDevices: CurrentDevices = CurrentDevices()
    val openDialog = remember {
        mutableStateOf(false)
    }
    var value by remember {
        mutableStateOf("")
    }
    val listItems = arrayOf(stringResource(R.string.ac_name), stringResource(R.string.door_name), stringResource(R.string.fridge_name), stringResource(R.string.lamp_name), stringResource(R.string.vacuum_name))
    val contextForToast = LocalContext.current.applicationContext
    var expanded by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf("")
    }
    var nameEnabled = false
    var dropDownEnabled = false
    Box(modifier = Modifier
        .fillMaxSize()
        //.background(MaterialTheme.colors.secondary)
    ) {
        FloatingActionButton(
            modifier = Modifier
                .zIndex(2f)
                .padding(all = 16.dp)
                .padding(bottom = 65.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = { openDialog.value = true },
            containerColor = MaterialTheme.colors.secondary
        ) {
            Icon(imageVector = Icons.Filled/*Default.Rounded*/.Add, contentDescription = "Add")
        }
        Column(
            modifier = Modifier
                .zIndex(1f)
                .fillMaxSize(),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.devices_title),
                fontSize = 25.sp,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, top = 16.dp, bottom = 20.dp)
            )
            if (openDialog.value){
                Dialog(
                    onDismissRequest = {
                        openDialog.value = false
                    }) {
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Box(modifier = Modifier
                            .width(300.dp)
                            .height(220.dp)
                        )
                        {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TextField(
                                    value = value,
                                    onValueChange = { newText ->
                                        value = newText
                                        nameEnabled = true
                                    },
                                    label = { Text(text = stringResource(R.string.device_name_msg)) },
                                    placeholder = { Text(text = stringResource(R.string.device_name_msg_2)) }
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                ExposedDropdownMenuBox(
                                    expanded = expanded,
                                    onExpandedChange = {
                                        expanded = !expanded
                                    }
                                ) {
                                    TextField(
                                        value = selected,
                                        onValueChange = {},
                                        readOnly = true,
                                        label = { Text(text = stringResource(R.string.device_type))},
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                                        }
                                    )
                                    ExposedDropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        listItems.forEach {
                                                selectedOption ->
                                            DropdownMenuItem(onClick = {
                                                selected = selectedOption
                                                Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                                                expanded = false
                                                dropDownEnabled = true
                                            }) {
                                                Text(text = selectedOption)
                                            }
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(15.dp))
                                Row() {
                                    Button(
                                        onClick = {
                                            openDialog.value = false
                                            value=""
                                            selected=""
                                            dropDownEnabled = false
                                            nameEnabled = false
                                        }
                                    ) {
                                        Text(text = stringResource(R.string.cancel))
                                    }
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Button(
                                        onClick = {
                                            openDialog.value = false
                                            value=""
                                            selected=""
                                            dropDownEnabled = false
                                            nameEnabled = false
                                        },
                                        enabled = dropDownEnabled && nameEnabled
                                    ) {
                                        Text(text = stringResource(R.string.confirm))
                                    }
                                }
                            }
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.height(3.dp))
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 65.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colors.primary),
                columns = GridCells.Adaptive(140.dp),//Fixed(2),
                contentPadding = PaddingValues(12.dp),
            ) {
                items(getTotalDevices()) {
                    for(device in currentDevices.devices) {
                        DeviceCard(
                            device = device
                        )
                    }
                }

            }
        }
    }
}

@DrawableRes
fun getImageResourceId(index: Int): Int {
    return when (index % 3) {
        0 -> R.drawable.ac
        1 -> R.drawable.puerta
        else -> R.drawable.lampara
    }
}

fun getTotalDevices(): Int{
    return 10
}