package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class PlayGroundActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}


@Composable
fun CustomSwitch() {

    val checked = remember { mutableStateOf(true) }

    Column {


       androidx.compose.material3.Switch(

           checked = checked.value ,
           onCheckedChange = { checked.value = it }
       )
        if ( checked.value ) {

            Text("switch is On")
        } else {

            Text("Switch is Off")
        }

    }
}

@Composable
fun CustomList ( items : List < String > ) {

    Column (

    ) {

        for (item in items ) {

            Text(item)
            Divider( color = Color.Green )
        }
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    CustomList( listOf("One","Two","Three","For","Five","Six"))

    //CustomSwitch()
}