package com.example.myapplication.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.util.devicesvm.RoutineViewModel

@Composable
fun RoutineScreen(routineViewModel: RoutineViewModel, onDismiss: () -> Unit){
    val uiState by routineViewModel.uiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = uiState.name.toString(),
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            modifier = Modifier.height(190.dp)
        ) {
            itemsIndexed(uiState.actions) { _, action ->
                Text(
                    text = "${action.device?.name} -> ${action.actionName}",
                    fontSize = 25.sp
                )
            }
        }



        Spacer(modifier = Modifier.height(20.dp))
        val context = LocalContext.current.applicationContext
        Button(
            onClick = {
                routineViewModel.executeRoutine()
                onDismiss()
              Toast.makeText(context,"Rutina ejecutada",Toast.LENGTH_SHORT).show()
                      },

            modifier = Modifier
                .height(80.dp)
                .width(130.dp),
        ) {
            Text(
                text = stringResource(R.string.exec),
                fontSize = 20.sp
            )
        }
    }
}