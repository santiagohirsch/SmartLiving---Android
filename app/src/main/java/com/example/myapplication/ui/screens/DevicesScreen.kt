import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.R

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun DevicesScreen() {
    val openDialog = remember {
        mutableStateOf(false)
    }
    var value by remember {
        mutableStateOf("")
    }
    val listItems = arrayOf("Santiago", "Bruno", "Mauro", "Santiago")
    val contextForToast = LocalContext.current.applicationContext
    var expaned by remember {
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
                    .padding(top = 16.dp)
                    .padding(start = 25.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(10) { index ->
                    ClickableImage(
                        resourceId = getImageResourceId(index),
                        contentDescription = "Image $index"
                    ) {
                        // Lógica de manejo de clics aquí
                    }
                }
            }
            Button(
                onClick = {
                    openDialog.value = true
                }
            ) {
               Text(text = "Agregar dispositivo")
            }
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
                                    expanded = expaned,
                                    onExpandedChange = {
                                        expaned = !expaned
                                    }
                                ) {
                                    TextField(
                                        value = selected,
                                        onValueChange = {},
                                        readOnly = true,
                                        label = { Text(text = "Tipo de dispositivo")},
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expaned)
                                        }
                                    )
                                    ExposedDropdownMenu(
                                        expanded = expaned,
                                        onDismissRequest = { expaned = false }
                                    ) {
                                        listItems.forEach {
                                            selectedOption ->
                                            DropdownMenuItem(onClick = {
                                                selected = selectedOption
                                                Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                                                expaned = false
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
        }
    }
}

@Composable
fun ClickableImage(
    resourceId: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(top = 5.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize()
        )
    }
}

// TODO: este nombre esta mal (nombre y retorna INT) o esta no es una funcion composable
// "The Compose function:
//      MUST be a noun: DoneButton()
//      NOT a verb or verb phrase: DrawTextField()
//      NOT a nouned preposition: TextFieldWithLink()
//      NOT an adjective: Bright()
//      NOT an adverb: Outside()
//      Nouns MAY be prefixed by descriptive adjectives: RoundIcon()"
@Composable
fun getImageResourceId(index: Int): Int {
    return when (index % 3) {
        0 -> R.drawable.ac
        1 -> R.drawable.puerta
        else -> R.drawable.lampara
    }
}
