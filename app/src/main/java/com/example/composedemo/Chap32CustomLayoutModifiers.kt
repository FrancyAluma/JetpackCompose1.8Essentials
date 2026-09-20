package com.example.composedemo

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlin.math.roundToInt

class Chap32CustomLayoutModifiers : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen32(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen32 ( modifier : Modifier= Modifier) {


   /*
    // for 1

    Box(
        modifier = Modifier
            .size(120.dp, 80.dp)
            .background(_root_ide_package_.androidx.compose.ui.graphics.Color.Green)

    ) {

        ColorBox(
          modifier = Modifier
              .exampleLayout(90,50)
              .background(_root_ide_package_.androidx.compose.ui.graphics.Color.Blue)
        )
    }*/


    Box(
        contentAlignment = Alignment.Center ,
        modifier = Modifier.size(120.dp, 80.dp)
    ) {

        Column {

            ColorBox(
                Modifier.exampleLayout(0f).background(
                    _root_ide_package_.androidx.compose.ui.graphics.Color.Blue
                )
            )

            ColorBox(
                Modifier.exampleLayout(0.25f).background(
                    _root_ide_package_.androidx.compose.ui.graphics.Color.Green
                )
            )

            ColorBox(
                Modifier.exampleLayout(0.5f).background(
                    _root_ide_package_.androidx.compose.ui.graphics.Color.Yellow
                )
            )

            ColorBox(
                Modifier.exampleLayout(0.25f).background(
                    _root_ide_package_.androidx.compose.ui.graphics.Color.Red
                )
            )

            ColorBox(
                Modifier.exampleLayout(0.0f).background(
                    _root_ide_package_.androidx.compose.ui.graphics.Color.Magenta
                )
            )
        }

    }


}


/*
// for 1

fun Modifier.exampleLayout(

    x : Int ,
    y : Int
) = layout{measurable ,constraints ->
    val placeable = measurable.measure(constraints)
    layout (
        placeable.width , placeable.height
    ) {
        placeable.placeRelative(x,y)
    }
}*/

fun Modifier.exampleLayout(
    fraction: Float

) = layout{measurable ,constraints ->
    val placeable = measurable.measure(constraints)
    val x = -(placeable.width * fraction).roundToInt()

    layout (
        placeable.width , placeable.height
    ) {
        placeable.placeRelative(x = x,y=0)
    }
}
@Composable
fun ColorBox ( modifier : Modifier = Modifier) {

    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(width  = 50.dp , height = 10.dp ).then(modifier)
    )


}
@Preview(showBackground = true)
@Composable
fun GreetingPreview12() {
    ComposeDemoTheme {
      Scaffold ( modifier=Modifier.fillMaxSize() ) {innerPadding ->
          MainScreen32(modifier = Modifier.padding(innerPadding))
      }
    }
}