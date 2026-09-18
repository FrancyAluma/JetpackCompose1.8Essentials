package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap28ComposingLayoutsWithRowAndColumn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MainScreen28( modifier = Modifier.padding(innerPadding) )
                }
            }
        }
    }
}

@Composable
fun MainScreen28 ( modifier : Modifier = Modifier) {

        /*Column (
            modifier
        ) {
            Row {
                Column {
                    TextCell("1")
                    TextCell("2")
                    TextCell("3")
                }

                Column {
                    TextCell("4")
                    TextCell("5")
                    TextCell("6")
                }
                Column {
                    TextCell("7")
                    TextCell("8")
                }
            }

            Row {

                TextCell("9")
                TextCell("10")
                TextCell("11")
            }

        }

*/


   /* Row (
      modifier.size(width = 400.dp, height = 200.dp)
*//*
ici on se doit de comprendre quelque chose , ce que ce Row ne comprend pas tout l'ecran,
sa taille est de largeur 400.dp et longueur de 200.dp. du coup on demande au TextCell de
se placer dans le milieu de ce longueur et de faire en sorte que ca occupe tout l'espace
de 400dp en se separant convenablement
* *//*
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {

        TextCell("9")
        TextCell("10")
        TextCell("11")
    }*/

 /*   Column (

       modifier.width(250.dp)
           .height(500.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom

    ) {

        TextCell("1")
        TextCell("2")
        TextCell("3")
    }*/

   /* Row (
    modifier = Modifier.height(300.dp)
    ) {

        TextCell("1" , Modifier.align(Alignment.Top) )
        TextCell("2" , modifier = Modifier.align(Alignment.CenterVertically))
        TextCell("3", modifier = Modifier.align(Alignment.Bottom))

    }*/

    /*Row {

        Text(
            "Large Text",
            Modifier.alignByBaseline()
            *//*
            L'importance de alignByBaseline () est qu'il permet a ce que le "Large Text" te
            le "Small Text" soient tous sur une meme ligne peu importe que l'autre soit
            plus grand que l'autre.
             *//*
            ,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold

        )

        Text (
            text = "Small Text",
            Modifier.alignByBaseline(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

    }
*/

   /* Row {

        Text(
            "Large Text\n \nMore Text\n \nLast Base",
          //  Modifier.alignByBaseline()
            Modifier.alignBy(LastBaseline)


            *//*
            L'importance de alignByBaseline () est qu'il permet a ce que le "Large Text" te
            le "Small Text" soient tous sur une meme ligne peu importe que l'autre soit
            plus grand que l'autre.
             *//*
            ,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold

        )

        Text (
            text = "Small Text",
            Modifier.alignByBaseline(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

    }*/



    /*Row (
      //  modifier = Modifier.width(500.dp)
    ){

        Text(
            "Large Text\n \nMore Text\n \nLast Base",
            Modifier.alignBy(FirstBaseline)

            *//*
            L'importance de alignByBaseline () est qu'il permet a ce que le "Large Text" te
            le "Small Text" soient tous sur une meme ligne peu importe que l'autre soit
            plus grand que l'autre.
             *//*
            ,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold

        )

        Text (
            text = "Small Text",
            modifier = Modifier.paddingFrom(
                alignmentLine = FirstBaseline , before = 50.dp, after = 0.dp
            ),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

    }
*/



    Row {

        /*
        The RowScope weight modifier allows the width of each child to be specified relative
        to its siblings. This works by assigning each child a weight percentage(between 0.0 and 1.0
        ). Two children assigned a weight of 0.5, for example, would each occupy half of the
        available space.
        * */

        TextCell("1" , modifier = Modifier.weight(weight = 0.2f, fill = true) )
        TextCell("2", modifier = Modifier.weight(weight = 0.4f, fill = true))
        TextCell("3",modifier = Modifier.weight(weight = 0.3f, fill = true))
    }

}

@Composable
fun TextCell ( text : String , modifier : Modifier = Modifier) {

    val cellModifier = modifier
        .padding(4.dp)
        .size(100.dp,100.dp)
        .border(width = 4.dp , color = _root_ide_package_.androidx.compose.ui.graphics.Color.Black)

    Text(

        text = text ,
        cellModifier.then(modifier),
        fontSize = 80.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center

    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    ComposeDemoTheme {
      /*Scaffold ( modifier= Modifier.fillMaxSize()) { innerPadding->
        MainScreen28(modifier = Modifier.padding(innerPadding))
      }*/

        MainScreen28()
    }
}