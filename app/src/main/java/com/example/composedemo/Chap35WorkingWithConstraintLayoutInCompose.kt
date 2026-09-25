package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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

    ConstraintLayout ( modifier.size(width = 350.dp , height = 220.dp) ) {

        val (button1 ,  button2 , button3) = createRefs()

     //   val guide = createGuidelineFromStart(fraction = .60f)

        val barrier = createEndBarrier(button1,button2)

        MyButton35(text = "Button1" , Modifier.constrainAs(button1) {

          // a. centerHorizontallyTo(parent)
           // a. top.linkTo(parent.top )
         //  a.  bottom.linkTo(button2.top)
            top.linkTo(parent.top , margin = 30.dp)
            start.linkTo(parent.start , margin = 8.dp)

        })

        MyButton35( text = "Button2" , Modifier.width(150.dp).constrainAs ( button2 ) {


            top.linkTo(button1.bottom , margin = 20.dp)
            start.linkTo(parent.start , margin = 8.dp)

        })

        MyButton35( text = "Button3" , Modifier.constrainAs(button3) {

            linkTo( parent.top , parent.bottom,
                topMargin = 8.dp, bottomMargin = 8.dp)

            linkTo(button1.end , parent.end , startMargin = 30.dp ,
                endMargin = 8.dp)
            start.linkTo(barrier , margin = 30.dp)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
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