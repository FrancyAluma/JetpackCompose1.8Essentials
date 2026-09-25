package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap36IntrinsincSizeInCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen36(modifier= Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen36 ( modifier : Modifier = Modifier ) {

    var textState by remember { mutableStateOf("") }
    val onTextChange = { text : String ->
        textState = text
    }

    Column ( modifier.width(200.dp)
        .padding(5.dp)) {

  // Applying IntrinsicSize.Max measurements

        Column (
            modifier = Modifier.width(IntrinsicSize.Max)

        ){

            Text(
                modifier = Modifier
                    .padding(start = 4.dp) ,
                text = textState //text = "Hello Compose"
            )

            Box(
                Modifier.height(10.dp)
                    .fillMaxWidth()
                    .background(_root_ide_package_.androidx.compose.ui.graphics.Color.Blue)
            )

            MyTextField36(text = textState , onTextChange = onTextChange)
        }
    }

}

@Composable
fun MyTextField36 ( text : String , onTextChange : (String) -> Unit ) {

    TextField(
        value = text ,
        onValueChange = onTextChange
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview16() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen36(modifier= Modifier.padding(innerPadding))
        }
    }
}