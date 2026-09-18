package com.example.composedemo

import android.R
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap27AnnotatedStringsEtBrushStyles : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MainScreen27( modifier = Modifier.padding(innerPadding) )
                }
            }
        }
    }
}

@Composable
fun MainScreen27 (modifier : Modifier = Modifier) {

    Column (
        modifier
    ) {

        SpanString()
        ParaString()
        BrushStyle()

    }
}

@Composable
fun BrushStyle () {

    val colorList : List<androidx.compose.ui.graphics.Color> = listOf(_root_ide_package_.androidx.compose.ui.graphics.Color.Red,
        _root_ide_package_.androidx.compose.ui.graphics.Color.Blue,
        _root_ide_package_.androidx.compose.ui.graphics.Color.Magenta,
        androidx.compose.ui.graphics.Color.Yellow,
        androidx.compose.ui.graphics.Color.Green,
        _root_ide_package_.androidx.compose.ui.graphics.Color.Red)

    Text(

        text = buildAnnotatedString {
            withStyle (
                style = SpanStyle (
                    fontWeight = FontWeight.Bold,
                    fontSize = 70.sp,
                    brush = Brush.linearGradient( colors = colorList)
                )
            ) {
                append("COMPOSE")
            }
        }
    )
}

@Composable
fun ParaString() {

    Text(

        buildAnnotatedString {
            append(
                "\nThis is some text that doesn't have any style applied to it. \n"
            )

            withStyle (
                style = ParagraphStyle (

                    lineHeight = 30.sp,
                    textIndent = TextIndent(
                        firstLine = 60.sp,
                        restLine = 25.sp
                    )

                )
            ){
                append("This is some text that is indented more on the first lines than " +
                        "the rest of the lines. It also has increased ine height. \n")
            }

           withStyle (
               style = ParagraphStyle (textAlign = TextAlign.End)
           ) {
            append("This is some text that is right aligned")
           }



        }
    )



}


@Composable
fun SpanString() {

    Text(
        buildAnnotatedString {
            withStyle (
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            ) {
             append("T")
            }

            withStyle (
               style = SpanStyle(
                   color = _root_ide_package_.androidx.compose.ui.graphics.Color.Gray
               )
            ) {
                append("his")
            }
            append("is")

            withStyle(
               style = SpanStyle (
                   fontWeight = FontWeight.Bold,
                   fontStyle = FontStyle.Italic,
                   color = _root_ide_package_.androidx.compose.ui.graphics.Color.Blue
               )
            ) {
                append("great!")
            }
        }
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    MainScreen27()
}