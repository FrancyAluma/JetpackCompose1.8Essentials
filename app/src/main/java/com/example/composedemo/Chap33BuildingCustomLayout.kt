package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap33BuildingCustomLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen33(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen33 ( modifier:Modifier = Modifier) {

    Box (
        modifier
    ) {

        CascadeLayout (
            spacing = 20
        ) {
        Box(modifier = Modifier.size(60.dp).background(_root_ide_package_.androidx.compose.ui.graphics.Color.Blue))
        Box(modifier = Modifier.size(80.dp, 40.dp).background(_root_ide_package_.androidx.compose.ui.graphics.Color.Red))
        Box(modifier = Modifier.size(90.dp,100.dp).background(_root_ide_package_.androidx.compose.ui.graphics.Color.Cyan))
        Box(modifier = Modifier.size(50.dp).background(_root_ide_package_.androidx.compose.ui.graphics.Color.Magenta))
        Box(modifier = Modifier.size(70.dp).background(_root_ide_package_.androidx.compose.ui.graphics.Color.Green))

        }

    }

}

@Composable
fun CascadeLayout(
    modifier : Modifier = Modifier ,
    spacing : Int = 0 ,
    content : @Composable () -> Unit
) {

    Layout (
        modifier = modifier ,
        content = content
    ) {measurables , constraints ->

        var indent = 0
        var yCoord = 0

        layout ( constraints.maxWidth , constraints.maxHeight ) {

            var yCoord = 0

            val placeables = measurables.map { measurable ->

                measurable.measure(constraints)
            }

            placeables.forEach { placeable ->

                placeable.placeRelative( x = indent , y = yCoord)
                indent += placeable.width + spacing
                yCoord += placeable.height + spacing

            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview13() {
    ComposeDemoTheme {
       Scaffold ( modifier = Modifier.fillMaxSize() ) { innerPadding->
           MainScreen33(modifier=Modifier.padding(innerPadding))

       }
    }
}