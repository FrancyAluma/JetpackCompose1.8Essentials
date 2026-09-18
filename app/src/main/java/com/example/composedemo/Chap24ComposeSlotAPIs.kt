package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap24ComposeSlotAPIs : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    WhereWecall(modifier= Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SlotDemo (
              topContent : @Composable () -> Unit,
              middleContent : @Composable ()-> Unit,
              bottomContent : @Composable () -> Unit ) {

    Column {

        topContent()
        middleContent()
        bottomContent()
    }
}


@Composable
fun WhereWecall (modifier : Modifier = Modifier ) {

    // This is how the SlotDemo could be structured.

  /* SlotDemo(
       middleContent = {ButtonDemo() },
        topContent = {Text("Top Text")},
        bottomContent = {Text( "Bottom Text" )}
    )*/

    // As with the single slot, this can be abbreviated for clarity :

   SlotDemo(
        { Text("Top Text") },
        { ButtonDemo() },
        { Text("Bottom Text") }
    )
}

@Composable
fun ButtonDemo () {

    Button(
        onClick = {}
    ) {

        Text(
            "Click Me"
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    ComposeDemoTheme {

        WhereWecall()
    }
}