package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.GenericButton

@Preview
@Composable
fun LandingScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(android.graphics.Color.parseColor("#2f2f4d"))) {
        //Image(painter = , contentDescription = null)
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                modifier = Modifier
                    .size(180.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = stringResource(R.string.supported_msg)
            )
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ac), contentDescription = "ac",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 40.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.puerta), contentDescription = "door",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 40.dp)
                )
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.aspiradora),
                    contentDescription = "vacuum",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 40.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.lampara), contentDescription = "lamp",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 40.dp)
                )
            }
            Image(painter = painterResource(id = R.drawable.heladera), contentDescription = "fridge" ,
                modifier = Modifier
                .size(100.dp)
                .padding(top = 40.dp)
                )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 70.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GenericButton(
                    label = stringResource(R.string.landing_msg),
                    clickEvent = {
                    }
                )
            }

        }
    }
}
