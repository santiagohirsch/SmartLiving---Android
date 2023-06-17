package com.example.myapplication.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R


@Preview
@Composable
fun LandingScreen(navController: NavHostController = rememberNavController()) {
    val openDialog = remember {
        mutableStateOf(false)
    }
    var switchOn by remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier.fillMaxWidth().padding(bottom = 0.dp), color = MaterialTheme.colors.background) {
        //Image(painter = , contentDescription = null)
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(100.dp)
            ){
                Surface(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    color = MaterialTheme.colors.primary
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(top = 20.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(end = 16.dp))
                NormalButton(navController)
            }
            Spacer(modifier = Modifier.height(3.dp))
            //Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = stringResource(R.string.supported_msg),
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(3.dp))
            Box(
                modifier = Modifier
                    .weight(1f) // Allow the LazyVerticalGrid to occupy any available height
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.defaultMinSize(minHeight = 200.dp)
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 65.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    //verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    reverseLayout = true,
                    columns = GridCells.Adaptive(100.dp),
                    contentPadding = PaddingValues(3.dp),
                ) {
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.ac),
                            contentDescription = "ac",
                            modifier = Modifier
                                .padding(top = 12.dp, bottom = 6.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .size(112.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.puerta),
                            contentDescription = "door",
                            modifier = Modifier
                                .padding(top = 12.dp, bottom = 6.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .size(112.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.aspiradora),
                            contentDescription = "vacuum",
                            modifier = Modifier
                                .padding(top = 12.dp, bottom = 6.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .size(112.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.lampara),
                            contentDescription = "lamp",
                            modifier = Modifier
                                .padding(top = 12.dp, bottom = 6.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .size(112.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.heladera),
                            contentDescription = "fridge",
                            modifier = Modifier
                                .padding(top = 12.dp, bottom = 6.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .size(112.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NormalButton(navController: NavHostController){
    val context = LocalContext.current
    OutlinedButton(
        onClick = {
            navController.navigate("devices_screen") {
                navController.graph.startDestinationRoute?.let { screenRoute ->
                    popUpTo(screenRoute) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        },
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .width(180.dp)
            .height(50.dp),
        border = BorderStroke(5.dp, MaterialTheme.colors.secondary)
    ){ Text(text = "Empieza ya!")}
}
