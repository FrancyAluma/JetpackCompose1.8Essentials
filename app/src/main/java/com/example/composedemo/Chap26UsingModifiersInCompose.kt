package com.example.composedemo

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap26UsingModifiersInCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  DemoScreen26( modifa = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DemoScreen26 (modifa : Modifier = Modifier) {

    val myModifier = modifa
        .border(width = 2.dp , color = _root_ide_package_.androidx.compose.ui.graphics.Color.Blue)
        .padding(all = 10.dp)

    /*
    The order in which modifiers are chained is of great significance to the resulting output.
    le resultat ne sera pqs le meme si c'etait :

     .padding(all = 10.dp)
     .border(width = 2.dp , color = _root_ide_package_.androidx.compose.ui.graphics.Color.Blue)


    * */

    val secondModifier = Modifier.height(100.dp)

    Column (
      modifier =  Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Text (
            "Hello Compose",
            myModifier.then(secondModifier),
            fontSize = 40.sp ,
            fontWeight = FontWeight.Bold
        )

        Spacer( modifier =  Modifier.height(16.dp))

        CustomImage(R.drawable.poloshirt)
    }

}

@Composable
fun CustomImage ( image : Int , modifiere : Modifier = Modifier) {

    Image(
        painter = painterResource(image) ,
        contentDescription = null,
         Modifier
            .padding (16.dp)
            .width(270.dp)
            .clip(shape = RoundedCornerShape(30.dp))
    )

}

@Preview(showBackground = true,)
@Composable
fun GreetingPreview6() {
    ComposeDemoTheme {
      DemoScreen26()
    }
}