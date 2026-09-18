package com.example.composedemo

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlin.random.Random
import kotlin.random.nextInt


data class ItemProperties (

    val color : androidx.compose.ui.graphics.Color,
    val width : Dp,
    val height : Dp

)
class Chap31FlowRowAndFlowColumnTutorial : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen31(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainScreen31 (modifier : Modifier = Modifier) {

    val items = (1..24).map{

        ItemProperties(
            width = Random.nextInt(20,100).dp,
            height = Random.nextInt(10,40).dp,
            color = _root_ide_package_.androidx.compose.ui.graphics.Color(
                Random.nextInt(255),
                Random.nextInt(255),
                Random.nextInt(255),
                255)
        )
    }

 /*   FlowRow (modifier.width(300.dp),
        horizontalArrangement = Arrangement.End) {

        *//*

        What FlowRow solves

A normal Row places children in a single horizontal line — if there are too many children to fit,
 they either get cut off or squished, because a Row never wraps to a new line on its own.

FlowRow fixes exactly that: it lays children out left-to-right, and when it runs out of horizontal
 space, it automatically wraps to the next line — just like how text wraps in a paragraph.
 You didn't write any wrapping logic yourself; FlowRow handles all of that measuring/positioning
 internally.
         *//*


        items.forEach { properties ->

            Box(

                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(2.dp)
                    .width(properties.width)
                    .height(30.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(properties.color)
            )

        }

    }*/



    FlowColumn (
        modifier
            .width(300.dp)
            .height(120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {

        items.forEachIndexed { index, properties ->


            var weight = 0.5f
            if (index % 2 == 0) {
                weight = 2f
            } else if (index % 3 == 0) {
                weight = 3f
            }

            Box(

                modifier = Modifier
                    .weight(weight)
                    .padding(2.dp)
                    .width(30.dp)
                    .height(30.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(properties.color)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    ComposeDemoTheme {
        Scaffold (modifier = Modifier.fillMaxSize()) {innerPadding ->
            MainScreen31(modifier = Modifier.padding(innerPadding))

        }
    }
}