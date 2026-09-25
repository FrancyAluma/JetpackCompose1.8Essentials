package com.example.composedemo

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap38AnOverviewOfListsAndGrids : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MainScreen38( modifier = Modifier.padding(innerPadding) )
                }
            }
        }
    }
}

@Composable
fun MainScreen38 ( modifier : Modifier = Modifier) {

    Column (

    ) {
        LazyVerticalGrid(GridCells.Adaptive(minSize = 60.dp),
            state = rememberLazyGridState(),
            contentPadding = PaddingValues(10.dp)
        ) {

            items(30) { index ->

                Card (

                    colors = CardDefaults.cardColors(

                        containerColor = MaterialTheme.colorScheme.primary
                    ) ,
                    modifier = Modifier.padding(5.dp)
                        .fillMaxSize()
                ) {

                    Text(
                        "$index" ,
                        textAlign = TextAlign.Center ,
                        fontSize = 30.sp ,
                        color = _root_ide_package_.androidx.compose.ui.graphics.Color.White,
                        modifier= Modifier.width(120.dp)


                    )
                }

            }


        }

        LazyVerticalGrid(
            GridCells.Fixed(3),
            state = rememberLazyGridState() ,
            contentPadding = PaddingValues(10.dp)
        ) {

            items(15) { index ->

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ) ,
                    modifier = Modifier.padding(5.dp).fillMaxSize()
                ) {
                    Text (

                        "$index " ,
                        fontSize = 35.sp ,
                        color = _root_ide_package_.androidx.compose.ui.graphics.Color.White,
                        textAlign = TextAlign.Center ,
                        modifier = Modifier.width(120.dp)
                    )
                }
            }

        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .shadow(
                    elevation = 50.dp ,
                    shape = CardDefaults.shape,
                    ambientColor = _root_ide_package_.androidx.compose.ui.graphics.Color.Red,
                    spotColor = _root_ide_package_.androidx.compose.ui.graphics.Color.Red
                ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {

            Column (

                modifier = Modifier.padding(15.dp)
                    .fillMaxWidth() ,
                  horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                  "Jetpack Compose" , fontSize = 30.sp
              )

                Text(
                    "Card Example" ,
                    fontSize = 20.sp
                    )

            }

        }

    }


    /*val colorNameList = listOf("Red","Green","Blue","Indigo")

    // Enabling scrolling with ScrollState

    val scrollstate = rememberScrollState()

    Column ( modifier = Modifier.verticalScroll(scrollstate)  ) {

        LazyColumn {itemsIndexed(colorNameList) {
                index , item ->
            Text( "$index = $item")
        }
        }


        LazyColumn () {
            items (100) {index->
                Text("This is item $index")
            }
        }

    }*/

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview18() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen38( modifier = Modifier.padding(innerPadding) )
        }
    }
}