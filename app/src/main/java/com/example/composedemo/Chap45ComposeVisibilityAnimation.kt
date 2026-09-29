package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedemo.ui.theme.ComposeDemoTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class Chap45ComposeVisibilityAnimation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen45( modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen45(modifier: Modifier = Modifier ) {

    var boxVisible by remember { mutableStateOf(true) }

    val onClick = { newState : Boolean ->
        boxVisible = newState
    }

    Column (
        modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row(

            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            CustomButton(text = "Show" ,
                targetState = true ,
                onClick = onClick)

            CustomButton(
                text = "Hide" ,
                targetState = true ,
                onClick = onClick
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        AnimatedVisibility( visible = boxVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 5500)) ,
            exit = fadeOut(animationSpec = tween(durationMillis = 5500))
        ) {

           Row {
               Box(
                   modifier = Modifier
                       .size(height = 150.dp, width = 150.dp)
                       .background(Color.Blue)
               )
               Spacer(modifier = Modifier.width(20.dp))

               Box(
                   Modifier
                       .animateEnterExit(
                           enter = slideInVertically(
                               animationSpec = tween(durationMillis = 5500)
                           ) ,
                           exit = slideOutVertically(
                               animationSpec = tween(durationMillis = 5500)
                           )
                       )
                       .size(width = 150.dp, height = 150.dp)
                       .background(Color.Red)
               )
           }
        }

    }


}

@Composable
fun CustomButton( text : String ,
                  targetState : Boolean,
                  onClick : (Boolean) -> Unit,
                  bgColor : Color = _root_ide_package_.androidx.compose.ui.graphics.Color.Blue) {

    Button(
        onClick = { onClick (targetState) },
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = Color.White
        )

    ) {

        Text(
            text
        )
    }

}

@Preview(showBackground = true, showSystemUi = true )
@Composable
fun GreetingPreview25() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen45( modifier = Modifier.padding(innerPadding))
        }
    }
}