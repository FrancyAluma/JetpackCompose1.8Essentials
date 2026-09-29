package com.example.composedemo

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring.DampingRatioHighBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap46ComposeStateDrivenAnimation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  MainScreen46( modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

enum class BoxColor {

    Red, Magenta
}

enum class BoxPosition{
    Start, End
}

@Composable
fun MotionDemo ( modifier : Modifier = Modifier) {


    val screenWidth = (LocalConfiguration.current.screenWidthDp.dp)

    var boxState by remember { mutableStateOf(BoxPosition.Start) }
    var boxSideLength = 70.dp

    val animatedOffset : Dp by animateDpAsState(
        targetValue = when (boxState) {
            BoxPosition.Start -> 0.dp
            BoxPosition.End -> screenWidth - boxSideLength
        } ,
        animationSpec = spring(dampingRatio = DampingRatioHighBouncy, stiffness = StiffnessVeryLow),
        label = "Motion"
    )

    Column (

        modifier = modifier.fillMaxWidth()

    ) {

        Box(

            modifier = Modifier
                .offset(x = animatedOffset , y = 20.dp)
                .size(boxSideLength)
                .background(_root_ide_package_.androidx.compose.ui.graphics.Color.Red)
        )

        Spacer( modifier = Modifier.height(50.dp))

        Button(
            onClick = {

                boxState = when (boxState) {

                    BoxPosition.Start -> BoxPosition.End
                    BoxPosition.End -> BoxPosition.Start
                }
            } ,
            modifier = Modifier.padding(20.dp)
                .align (Alignment.CenterHorizontally)
        ) {

            Text(
                text = "Move Box"
            )
        }

    }

}


@Composable
fun TransitionDemo ( modifier : Modifier = Modifier) {

    var boxState by remember { mutableStateOf(BoxPosition.Start) }
    var screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val transition = updateTransition(targetState = boxState,
        label = "Color and Motion")

    val animatedColor : androidx.compose.ui.graphics.Color by transition.animateColor (


        transitionSpec = {
            tween(4000)
        } , label = "colorAnimation"
    ) { state->
        when(state) {
            BoxPosition.Start -> _root_ide_package_.androidx.compose.ui.graphics.Color.Red
            BoxPosition.End -> _root_ide_package_.androidx.compose.ui.graphics.Color.Magenta
        }

    }

    val animatedOffset : Dp by transition.animateDp (
        transitionSpec = {
            tween(4000)
        } ,
        label = "offsetAnimation"
    ) {  state->
        when(state) {
            BoxPosition.Start -> 0.dp
            BoxPosition.End -> screenWidth - 70.dp
        }

    }

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {

        Box(

            modifier = Modifier
                .offset(x = animatedOffset , y = 20.dp)
                .size(70.dp)
                .background(animatedColor)
        )

        Spacer( modifier = Modifier.height(50.dp))

        Button(
            onClick = {

                boxState = when (boxState) {

                    BoxPosition.Start -> BoxPosition.End
                    BoxPosition.End -> BoxPosition.Start
                }
            } ,
            modifier = Modifier.padding(20.dp)
                .align (Alignment.CenterHorizontally)
        ) {

            Text(
                text = "Start Animation"
            )
        }
    }

}

@Composable
fun ColorChangeDemo( modifier: Modifier = Modifier) {

    var colorState by remember { mutableStateOf(BoxColor.Red) }

    val animatedColor : androidx.compose.ui.graphics.Color by animateColorAsState(
        targetValue = when(colorState) {

            BoxColor.Red -> _root_ide_package_.androidx.compose.ui.graphics.Color.Magenta
            BoxColor.Magenta -> _root_ide_package_.androidx.compose.ui.graphics.Color.Red
        } ,
        animationSpec = tween(4500) , label = "ColorChange"
    )

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()

    ) {

        Box(
            modifier = Modifier
                .padding(20.dp)
                .size(200.dp)
                .background(animatedColor)
        )

        Button(
            onClick = {
                colorState = when (colorState) {

                    BoxColor.Red -> BoxColor.Magenta
                    BoxColor.Magenta -> BoxColor.Red
                }
            } ,
            modifier = Modifier.padding(10.dp)
        ) {

            Text(
                text = "Change Color"
            )
        }

    }

}
@Composable
fun MainScreen46 ( modifier : Modifier = Modifier) {

    var rotated by remember { mutableStateOf(false) }

    val angle by animateFloatAsState(
        targetValue = if (rotated) 360f else 0f ,
        animationSpec = tween(durationMillis = 2500 , easing = LinearEasing), label = "Rotate"
    )

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {


        Image(

            painter = painterResource(R.drawable.propeller2) ,
            contentDescription = "fan",
            modifier = Modifier
                .rotate(angle)
                .padding(10.dp)
                .size(300.dp)

        )

        Button(
            onClick = {rotated = !rotated},
            modifier = Modifier.padding(10.dp)
        ) {

            Text(
                "Rotate Propeller"
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview26() {
    ComposeDemoTheme {
        /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen46( modifier = Modifier.padding(innerPadding))
        }*/

      //  ColorChangeDemo(modifier = Modifier)

      //  MotionDemo(modifier = Modifier)

        TransitionDemo(modifier = Modifier)
    }
}