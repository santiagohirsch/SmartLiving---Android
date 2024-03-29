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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
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
import androidx.compose.runtime.collectAsState
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
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.data.network.models.Device
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
fun DevicesScreen(
    viewModel: DevicesViewModel,
    isPhone: Boolean = true
) {
    val name = stringResource(R.string.name)
    val type = stringResource(R.string.type)
    val openDialog = remember {
        mutableStateOf(false)
    }
    var value by remember {
        mutableStateOf("")
    }
    val listItems = arrayOf(stringResource(R.string.ac_name), stringResource(R.string.door_name), stringResource(R.string.fridge_name), stringResource(R.string.lamp_name), stringResource(R.string.vacuum_name))
    val sortOptions = arrayOf(stringResource(R.string.default_msg), stringResource(R.string.name), stringResource(R.string.type))
    val contextForToast = LocalContext.current.applicationContext
    var expanded by remember {
        mutableStateOf(false)
    }
    var expandedSort by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf("")
    }
    var nameEnabled = false
    var dropDownEnabled = false

    var sortArray by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier
        .fillMaxSize()
        //.background(MaterialTheme.colors.secondary)
    ) {
        FloatingActionButton(
            modifier = Modifier
                .zIndex(2f)
                .padding(all = 16.dp)
                .padding(bottom = 65.dp)
                .align(alignment = Alignment.BottomEnd)
                .size(if (!isPhone) (100.dp) else (50.dp))
            ,
            onClick = { openDialog.value = true },
            containerColor = MaterialTheme.colors.secondary
        ) {
            Icon(
                imageVector = Icons.Default/*Default.Rounded*/.Add,
                contentDescription = "Add"
            )
        }
        Column(
            modifier = Modifier
                .zIndex(1f)
                .fillMaxSize(),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.devices_title),
                    fontSize = if (isPhone) 25.sp else 70.sp,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 25.dp, top = 16.dp, bottom = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f)) // Add a Spacer with weight to push the dropdown menu to the right
                ExposedDropdownMenuBox(
                    expanded = expandedSort,
                    onExpandedChange = {
                        expandedSort = !expandedSort
                    },
                    modifier = Modifier.width(150.dp) // Adjust the width of the dropdown menu here
                ) {
                    Row(
                        modifier = Modifier.padding(end = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = sortArray,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(text = stringResource(R.string.sort_by)) },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSort)
                            },
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }
                    ExposedDropdownMenu(
                        expanded = expandedSort,
                        onDismissRequest = { expandedSort = false }
                    ) {
                        sortOptions.forEach { selectedOption ->
                            DropdownMenuItem(onClick = {
                                sortArray = selectedOption
                                Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT)
                                    .show()
                                expandedSort = false
                                dropDownEnabled = true
                            }) {
                                Text(text = selectedOption)
                            }
                        }
                    }
                }
            }



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
                                    val typeId = deviceTypeToId(selected = selected)
                                    Button(
                                        onClick = {
                                            viewModel.addNewDevice(value.toString(), typeId.toString())
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

                viewModel.getAllDevices()
                var devices = viewModel.getCurrentDevices()
                when(sortArray) {
                    name -> devices = viewModel.getCurrentDevices().sortedBy { it.name } as MutableList<Device>
                    type -> devices = viewModel.getCurrentDevices().sortedBy { it.type?.name } as MutableList<Device>
                }
                itemsIndexed(devices) { _, device ->
                    when (device.type?.name) {
                        "ac" -> DeviceCard(AcViewModel(device))
                        "refrigerator" -> DeviceCard(RefrigeratorViewModel(device))
                        "lamp" -> DeviceCard(LampViewModel(device))
                        "vacuum" -> DeviceCard(VacuumViewModel(device))
                        "door" -> DeviceCard(DoorViewModel(device))
                    }
                }
            }
        }
    }

}

@Composable
fun deviceTypeToId(selected: String): String {
    when(selected) {
        stringResource(R.string.ac_name) -> return "li6cbv5sdlatti0j"
        stringResource(R.string.door_name) -> return "lsf78ly0eqrjbz91"
        stringResource(R.string.lamp_name) -> return "go46xmbqeomjrsjr"
        stringResource(R.string.fridge_name) -> return "rnizejqr2di0okho"
        stringResource(R.string.vacuum_name) -> return "ofglvd9gqx8yfl3l"
    }
    return ""
}

