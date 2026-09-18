package com.example.composedemo

import android.os.Bundle
import android.widget.CheckBox
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap25ComposeSlotAPITutorial : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MainScreen(modifier=Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen (modifier : Modifier = Modifier) {

    var linearSelected by remember { mutableStateOf(true) }

    var imageSelected by remember { mutableStateOf(true) }

    var onLinearClick = { value : Boolean ->
        linearSelected = value
    }

    var onTitleClick = { value : Boolean ->
        imageSelected = value
    }

    ScreenContent (
        linearSelected = linearSelected,
        imageSelected = imageSelected  ,
        onLinearClick = onLinearClick,
        onTitleClick =onTitleClick,
          titleContent = {
           if (imageSelected) {

               TitleImage(
                   drawing = R.drawable.baseline_wb_cloudy_24
               )
           }
               else {
                   Text (
                       "Downloading",
                       style = MaterialTheme.typography.headlineSmall,
                       modifier = Modifier.padding (30.dp)
                   )
               }


        } ,
        progressContent =  {

            if(
              linearSelected
            ) {
                CircularProgressIndicator(Modifier.size(200.dp),
                    strokeWidth = 18.dp)
            } else {
                LinearProgressIndicator(Modifier.height(40.dp))
            }
        }

    )

}

@Composable
fun ScreenContent (
    linearSelected : Boolean ,
    imageSelected : Boolean ,
    onTitleClick : (Boolean) -> Unit,
    onLinearClick : (Boolean)-> Unit,
    titleContent : @Composable () -> Unit ,
    progressContent : @Composable ()-> Unit
) {

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        titleContent()
        progressContent()
        CheckBoxes (linearSelected,imageSelected , onTitleClick  ,onLinearClick)
    }
}

@Composable
fun TitleImage ( drawing: Int) {

    Image(
        painter = painterResource(drawing),
        contentDescription = "title image",
        modifier = Modifier.size(150.dp)
    )

}


@Composable
fun CheckBoxes (
    linearSelected : Boolean ,
    imageSelected : Boolean ,
    onTitleClick : (Boolean) -> Unit,
    onLinearClick : (Boolean)-> Unit
) {

    Row (
      modifier = Modifier.padding(20.dp) ,
        verticalAlignment = Alignment.CenterVertically
    ){


        Checkbox(

            checked = imageSelected,
            onCheckedChange = onTitleClick
        )

        Text (

            text = "Image Title"
        )

        Spacer(Modifier.width(20.dp))

        Checkbox(
            checked = linearSelected,
            onCheckedChange = onLinearClick
        )

        Text(
            "Linear Progress"
        )
    }
}



@Preview( showSystemUi = true)
@Composable
fun GreetingPreview5() {
    ComposeDemoTheme {
      /* CheckBoxes(
           linearSelected = true ,
           imageSelected = false ,
           onTitleClick = {} ,
           onLinearClick = {}
       )*/

        Scaffold ( modifier = Modifier.fillMaxSize() ) {innerPadding ->
            MainScreen(modifier= Modifier.padding(innerPadding))
        }
    }
}