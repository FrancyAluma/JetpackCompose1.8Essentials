package com.example.composedemo

import android.graphics.PathEffect
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlin.math.PI

class Chap47CanvasGraphicsDrawingIncompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  MainScreen47( modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen47 ( modifier : Modifier = Modifier) {

  //  DrawLine()

   // DrawRect()

   // DrawCircle()

   // DrawOval()

  //  GradientFill()

   // RadialFill()

   // ShadowCircle()

  //  DrawArc()

   // DrawPath()

   // DrawPoints()

   // DrawImage()

    DrawText()
}


@Composable
fun DrawText () {

    val colorList : List<Color> = listOf(
        Color.Red ,
        Color.Blue ,
        Color.Magenta ,
        Color.Yellow,
        Color.Green,

    )

    val textMeasurer = rememberTextMeasurer()

    val annotatedText = buildAnnotatedString {
        withStyle(

            style = SpanStyle (

                fontSize = 60.sp,
                fontWeight = FontWeight.ExtraBold,
                brush = Brush.verticalGradient(colors = colorList)
            )
        ) {

            append("Text  Drawing")
        }
    }

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {


        val dimensions = textMeasurer.measure(annotatedText)
        drawRect(
            brush = Brush.horizontalGradient(colors = colorList),
            size = dimensions.size.toSize()
        )

        drawText( textMeasurer , annotatedText)
    }


}
@Composable
fun DrawImage () {

    val image = ImageBitmap.imageResource(id = R.drawable.ima1)

    Canvas(

      modifier = Modifier.size(360.dp, 270.dp)
    ) {

        drawImage(
            image = image ,
            topLeft = Offset(x = 0f , y = 0f)
        )
    }

}

@Composable
fun DrawPoints() {

    Canvas(
        modifier = Modifier.size(300.dp)
    ) {

        val height = size.height
        val width = size.width

        val points = mutableListOf<Offset>()

        for (x in 0..size.width.toInt()) {

            val y = (kotlin.math.sin( x * (2f * PI / width) )
            * (height / 2) + (height / 2)).toFloat()
            points.add(Offset(x.toFloat(),y))
        }

        drawPoints(
            points = points ,
            strokeWidth = 3f ,
            pointMode = PointMode.Points,
            color = Color.Blue
        )
    }
}
@Composable
fun DrawPath () {


    Canvas(modifier = Modifier.size(300.dp) ) {


        val path = Path().apply {

            moveTo(0f,0f)
            quadraticBezierTo(50.dp.toPx() , 200.dp.toPx() ,300.dp.toPx(),300.dp.toPx())
            lineTo(270.dp.toPx() , 100.dp.toPx())
            quadraticBezierTo(60.dp.toPx() , 80.dp.toPx(),0f,0f)
            close()

        }

        drawPath(
            path = path ,
            Color.Blue
        )
    }
}

@Composable
fun DrawArc(){

    Canvas( modifier = Modifier.size(300.dp) ) {

        drawArc(

            Color.Blue,
            startAngle = 20f,
            sweepAngle = 90f,
            useCenter = true,
            size = Size(250.dp.toPx(), 250.dp.toPx())

        )

    }
}

@Composable
fun ShadowCircle () {

    Canvas(
        modifier = Modifier.size(300.dp)
    ) {


        val radius = 150.dp.toPx()
        val colorList : List<Color> = listOf(
            Color.Blue ,
            Color.Black
        )

        val brush = Brush.horizontalGradient(
            colors = colorList,
            startX = 0f,
            endX = 300.dp.toPx(),
            tileMode = TileMode.Repeated
        )

        drawCircle(
            brush = brush ,
            radius = radius

            // Interessant pour wiist
        )


    }

}


@Composable
fun GradientFill () {

    Canvas(
        modifier = Modifier.size(300.dp)
    ) {

        val canvasSize = size

        val colorList : List<Color> = listOf(
            Color.Red ,
            Color.Blue ,
            Color.Magenta ,
            Color.Yellow,
            Color.Green,
            Color.Cyan
        )

        val brush = Brush.horizontalGradient(
            colors = colorList,
            startX = 0f ,
            endX = 300.dp.toPx(),
            tileMode = TileMode.Repeated
        )

        drawRect(
            brush = brush,
            size = canvasSize
        )
    }
}

@Composable
fun RadialFill() {


    Canvas(
        modifier = Modifier.size(300.dp)
    ) {

      val radius = 150.dp.toPx()
        val colorList : List<Color> = listOf(
            Color.Red ,
            Color.Blue ,
            Color.Magenta ,
            Color.Yellow,
            Color.Green,
            Color.Cyan
        )

        val brush = Brush.radialGradient(
            colors = colorList,
            center = center,
            radius = radius,
            tileMode = TileMode.Repeated
        )

        drawCircle(
            brush = brush,
            center = center,
            radius = radius
        )

    }
}

@Composable
fun DrawOval () {

    Canvas(

        modifier = Modifier.size(300.dp)
    ) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        drawOval(

            color = _root_ide_package_.androidx.compose.ui.graphics.Color.Blue,
            topLeft = Offset(x = 25.dp.toPx() , y = 90.dp.toPx()),
            size = Size(
                width = canvasWidth - 50.dp.toPx() ,
                height = canvasHeight/2 - 50.dp.toPx()
            ) ,
            style = Stroke(width = 12.dp.toPx())
        )
    }
}
@Composable
fun DrawLine() {

    Canvas(
        modifier = Modifier.size(300.dp)
    ) {

        val height = size.height
        val width = size.width

        drawLine(
            start = Offset(x = 0f , y =200f) ,
            end = Offset(x = width , y = height),
            color = _root_ide_package_.androidx.compose.ui.graphics.Color.Blue,
            strokeWidth = 16.0f,
            pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                floatArrayOf(30f,10f,10f,10f) , phase = 0f
            )
        )
    }
}

@Composable
fun DrawRect () {

    Canvas( modifier = Modifier.size(300.dp)) {

     //   val size = Size(600f, 250f)

        drawRect(
            color = _root_ide_package_.androidx.compose.ui.graphics.Color.Blue ,
            topLeft = Offset(x= 350f , y = 300f),
            size = size / 2f
        )

        // Le reste de comment drawRect , voir le livre page 440 et 441

    }
}

@Composable
fun DrawCircle () {

    Canvas(
        modifier = Modifier.size(300.dp)
    ) {

        drawCircle(

            color = _root_ide_package_.androidx.compose.ui.graphics.Color.Blue,
            center = center,
            radius = 120.dp.toPx()
        )

    }


}

@Preview(showBackground = true )
@Composable
fun GreetingPreview27() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen47( modifier = Modifier.padding(innerPadding))
        }
    }
}