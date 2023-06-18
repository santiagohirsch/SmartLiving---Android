import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.R
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.ui.components.DeviceCard
import com.example.myapplication.util.devicesrep.CurrentDevices
import com.example.myapplication.util.devicesvm.AcViewModel
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.DevicesViewModel
import com.example.myapplication.util.devicesvm.DoorViewModel
import com.example.myapplication.util.devicesvm.LampViewModel
import com.example.myapplication.util.devicesvm.RefrigeratorViewModel
import com.example.myapplication.util.devicesvm.VacuumViewModel

@OptIn(ExperimentalMaterialApi::class)

@Composable
fun DevicesScreen(viewModel: DevicesViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val openDialog = remember {
        mutableStateOf(false)
    }
    var value by remember {
        mutableStateOf("")
    }
    val listItems = arrayOf("Horno", "Lampara", "Heladera", "Puerta", "Aspiradora")
    val contextForToast = LocalContext.current.applicationContext
    var expanded by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf("")
    }
    var nameEnabled = false
    var dropDownEnabled = false
    Surface(modifier = Modifier.fillMaxSize(), color = Color(android.graphics.Color.parseColor("#2f2f4d"))) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.devicesTitle),
                fontSize = 25.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, top = 16.dp, bottom = 10.dp)
            )
            if (openDialog.value){
                Dialog(
                    onDismissRequest = {
                        openDialog.value = false
                    }) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Box(modifier = Modifier
                            .width(300.dp)
                            .height(300.dp)
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
                                    label = { Text(text = "Nombre del dispositivo") },
                                    placeholder = { Text(text = "Escriba el nombre del dispositivo") }
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
                                        label = { Text(text = "Tipo de dispositivo")},
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
                                        Text(text = "Cancelar")
                                    }
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Button(
                                        onClick = {
                                            openDialog.value = false
                                            value=""
                                            selected=""
                                            dropDownEnabled = false
                                            nameEnabled = false
                                            viewModel.addNewDevice("elwey","go46xmbqeomjrsjr" )
                                        },
                                        enabled = dropDownEnabled && nameEnabled
                                    ) {
                                        Text(text = "Confirmar")
                                    }
                                }
                            }
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(R.color.secondary_button)),
                columns = GridCells.Adaptive(140.dp),//Fixed(2),
                contentPadding = PaddingValues(12.dp),
            ) {
                viewModel.getAllDevices()
                items(viewModel.getCurrentDevices().size) { index ->
                    val device = viewModel.getCurrentDevices()[index]
                    println(device.type?.name)
                    when (device.type?.name) {
                        "ac" -> DeviceCard(AcViewModel(device.name, device.id))
                        "refrigerator" -> DeviceCard(RefrigeratorViewModel(device.name, device.id))
                        "lamp" -> DeviceCard(LampViewModel(device.name, device.id))
                        "vacuum" -> DeviceCard(VacuumViewModel(device.name, device.id))
                        "door" -> DeviceCard(DoorViewModel(device.name, device.id))
                    }
                }
            }
            }
        }
    }

