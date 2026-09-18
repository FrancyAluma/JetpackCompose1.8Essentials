package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap29BoxLayoutsInCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MainScreen29(modifier = Modifier.padding(
                       innerPadding
                   ))
                }
            }
        }
    }
}

@Composable
fun MainScreen29 ( modifier : Modifier = Modifier) {


       Box (
            modifier.size(400.dp, 400.dp)
                .clip(RoundedCornerShape(50.dp))
                .border(width = 4.dp ,
                    _root_ide_package_.androidx.compose.ui.graphics.Color.Red,
                    shape = RoundedCornerShape(50.dp))
                .background(_root_ide_package_.androidx.compose.ui.graphics.Color.Blue),

            contentAlignment = Alignment.CenterEnd
        ) {

            val height = 200.dp
            val width = 200.dp

            TextCell29( "1" , Modifier.size(width = width , height = height) )
            TextCell29( "2" , Modifier.size(width = width , height = height) )
            TextCell29( "3" , Modifier.size(width = width , height = height) )


        }


  /*  Box (
        modifier.size(400.dp,150.dp)
    ) {

        TextCell29("TopStart", Modifier.align(Alignment.TopStart), fontSize = 8 )
        TextCell29("TopCenter", Modifier.align(Alignment.TopCenter), fontSize = 8 )
        TextCell29("TopEnd", Modifier.align(Alignment.TopEnd), fontSize = 8 )

        TextCell29("CenterStart", Modifier.align(Alignment.CenterStart), fontSize = 8 )
        TextCell29("Center", Modifier.align(Alignment.Center), fontSize = 8 )
        TextCell29("CenterEnd", Modifier.align(Alignment.CenterEnd) , fontSize = 8 )

        TextCell29("BottomStart", Modifier.align(Alignment.BottomStart), fontSize = 8 )
        TextCell29("BottomCenter", Modifier.align(Alignment.BottomCenter) , fontSize = 8)
        TextCell29("BottomEnd", Modifier.align(Alignment.BottomEnd) , fontSize = 8)

    }*/

 /*   Box( modifier.size(200.dp)
        .clip(CircleShape)
        .background(_root_ide_package_.androidx.compose.ui.graphics.Color.Blue)
    )*/

    /*Box (
        modifier.size(200.dp)
        .clip(CutCornerShape(30.dp))
        .background(_root_ide_package_.androidx.compose.ui.graphics.Color.Blue)
    )*/



}


@Composable
fun TextCell29( text : String , modifier: Modifier = Modifier , fontSize : Int = 170 ) {

    val cellModifier = modifier
        .padding (4.dp)
        .border(width = 5.dp , color = _root_ide_package_.androidx.compose.ui.graphics.Color.Green)

    Surface (

        cellModifier.then(modifier)
    ) {

        Text(
            text = text,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview9() {
    ComposeDemoTheme {
      // MainScreen29()

        Scaffold (
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            MainScreen29(modifier = Modifier.padding(innerPadding))
        }
    }
}