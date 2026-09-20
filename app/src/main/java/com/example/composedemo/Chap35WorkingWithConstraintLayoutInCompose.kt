package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap35WorkingWithConstraintLayoutInCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen35(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun VoyonsVoir ( modifier : Modifier = Modifier ) {

    ConstraintLayout (


    ) {

        val (button, text) = createRefs()

        Button(

            onClick = {} ,
            modifier = Modifier.constrainAs(button){ top.linkTo(parent.top , margin = 16.dp) }
        ) {

            Text(
                "Button"
            )
        }

        Text(
            "Text" ,
            modifier = Modifier.constrainAs(text) { top.linkTo(button.bottom, margin = 16.dp)  }
        )
    }


}
@Composable
fun MainScreen35 ( modifier : Modifier = Modifier) {

    ConstraintLayout ( modifier.size(width = 400.dp , height = 250.dp) ) {

        val (button1 ,  button2 , button3) = createRefs()

        val guide = createGuidelineFromStart(fraction = .60f)

        MyButton35(text = "Button1" , Modifier.constrainAs(button1) {

          // a. centerHorizontallyTo(parent)
           // a. top.linkTo(parent.top )
         //  a.  bottom.linkTo(button2.top)
            top.linkTo(parent.top , margin = 30.dp)
            end.linkTo(guide, margin = 30.dp)

        })

        MyButton35( text = "Button2" , Modifier.constrainAs ( button2 ) {


            top.linkTo(button1.bottom , margin = 20.dp)
            end.linkTo(guide , margin = 40.dp)

        })

        MyButton35( text = "Button3" , Modifier.constrainAs(button3) {

            top.linkTo(button2.bottom , margin = 40.dp)
            end.linkTo(guide, margin = 20.dp)
        } )

    }


}

@Composable
fun MyButton35 (  text : String , modifier : Modifier = Modifier) {

    Button(
       onClick = { },
        modifier = modifier
    ) {

        Text( text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview15() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen35(modifier = Modifier.padding(innerPadding))
        }


      /*  Scaffold  ( modifier = Modifier.fillMaxSize() ){innerPadding ->
            VoyonsVoir(modifier = Modifier.padding(innerPadding))

        }*/
    }
}