package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap22ComposeStateAndRecomposition : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {

               /* Scaffold (modifier = Modifier.fillMaxSize() ) {innerPadding->
                    greeting("Georges" ,
                        modifieer = Modifier.padding(innerPadding)
                    )
                }*/

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   DemoScreen22(Modifier.padding(innerPadding) )
                }



            }
        }
    }
}

@Composable
fun greeting( nom : String , modifieer : Modifier = Modifier) {

    // Cette function m'a aide' a' comprendre le innerPadding

  Text(
      text = "Salut $nom" ,
      modifier = modifieer
  )
}

@Composable
fun DemoScreen22 (modifieer : Modifier = Modifier) {

   // var textState by remember { mutableStateOf("") }

    var textState by rememberSaveable() { mutableStateOf("") }

    /*
    After the rotation , we will notuce that the Texfield is now blank and the text entered
    has been lost. In situations where state needs to be retained through configuration changes,
    Compose provides the "rememberSaveable" keyword.
    * */

    val onTextChange = {text : String ->
        textState = text
    }

    Column(
        modifier = modifieer
    ) {
        MyTextField22(textState,onTextChange)
        FunctionA()
    }
}

@Composable
fun FunctionA () {

    var switchStateA by remember { mutableStateOf(true) }

    val onSwitchChangeA = {value : Boolean ->
        switchStateA = value
    }

    FunctionB(
        switchState = switchStateA ,
        onSwitchChange = onSwitchChangeA
    )
}

@Composable
fun FunctionB (switchState : Boolean , onSwitchChange : (Boolean)-> Unit) {

    Switch(
        checked = switchState,
        onCheckedChange = onSwitchChange

    )
}

@Composable
fun MyTextField22 ( text : String , onTextChange : (String)-> Unit ) {


    TextField(
        value = text ,
        onValueChange = onTextChange
    )


    // Cette technique est la plus utilisee mais on a encore 2 autres techniques
     //A
    /*var textState by remember { mutableStateOf("") }

    val onTextChange = {text : String ->
        textState = text

    }

    TextField(
        value = textState,
        onValueChange = onTextChange
    )*/

    // B et C

    /*//B
    var textState = remember { mutableStateOf("") }

    val onTextChange = { text : String ->
        textState.value = text
    }

    TextField(
        value = textState.value,
        onValueChange = onTextChange
    )

    //C

    var (texValue,setText) = remember { mutableStateOf("") }

    val onTextChange = {text : String ->
        setText(text)
    }

    TextField(
        value = texValue,
        onValueChange = onTextChange
    ) */

}



@Preview(showBackground = true, showSystemUi = true )
@Composable
fun GreetingPreview2() {
    ComposeDemoTheme {

       /* Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
            greeting("Georges", modifieer = Modifier.padding(innerPadding))

        Ce padding que l'on met en bas est tres important , surtout quand tu mets le showSystemUi.
        Parce que ca permet a e que tes contenus soient en dessous de la date ou de l'heure .
        Et ne jamais oublier dans le composable qui sera affecte par le innerPadding de reprendre
        le "modifieer"

        }*/


      Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DemoScreen22(Modifier.padding(innerPadding) )
        }



    }
}