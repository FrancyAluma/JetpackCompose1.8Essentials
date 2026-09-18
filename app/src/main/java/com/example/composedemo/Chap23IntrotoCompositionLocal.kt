package com.example.composedemo


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap23IntrotoCompositionLocal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  Composable1(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

val LocalColor = staticCompositionLocalOf {
    _root_ide_package_.androidx.compose.ui.graphics.Color(0xFFffdbcf)}

@Composable
fun Composable1 (modifier : Modifier = Modifier) {

    val color = if (isSystemInDarkTheme()) {
        _root_ide_package_.androidx.compose.ui.graphics.Color(0xFFa08d87)
    } else {
        _root_ide_package_.androidx.compose.ui.graphics.Color(0xFFffdbcf)
    }

    Column {

        Composable2()
        CompositionLocalProvider(LocalColor provides color) {
            Composable3()
        }


    }
}

@Composable
fun Composable2 (modifier:Modifier = Modifier) {


   CompositionLocalProvider(
       LocalColor provides _root_ide_package_.androidx.compose.ui.graphics.Color.Blue
   ) {
       Composable4()
   }

   // Composable4()
}

@Composable
fun Composable3 (modifier:Modifier = Modifier) {

    Text(
        "Composable 3",
        modifier.background(LocalColor.current)
    )
   CompositionLocalProvider(LocalColor provides _root_ide_package_.androidx.compose.ui.graphics.Color.Red) {
       Composable5()
   }
}

@Composable
fun Composable4 (modifier:Modifier = Modifier) {

    Composable6()
}

@Composable
fun Composable5 (modifier:Modifier = Modifier) {


    Text(
        "Composable 5",
        modifier.background(LocalColor.current)

    )

   CompositionLocalProvider(
       LocalColor provides _root_ide_package_.androidx.compose.ui.graphics.Color.Green
   ) {
       Composable7()
   }

    CompositionLocalProvider(
        LocalColor provides _root_ide_package_.androidx.compose.ui.graphics.Color.Yellow
    ) {
        Composable8()
    }

}

@Composable
fun Composable6 (modifier:Modifier = Modifier) {

    Text(
        "Composable 6" ,
        modifier= Modifier.background(LocalColor.current)
    )

}

@Composable
fun Composable7 (modifier:Modifier = Modifier) {

    Text(
        "Composable 7",
        modifier = Modifier.background(LocalColor.current)
    )

}

@Composable
fun Composable8 (modifier:Modifier = Modifier) {

    Text(
        "Composable 8",
        modifier.background(LocalColor.current)
    )
}



@Preview(showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
    /*
    Ici nous avons active le darkmode. Et nous avons fait comprendre a l'App que lorsque c'est
    en darkMode, nous devons faire mettre la couleur un peu sombre. Ref: Composable1()
     */
)
@Composable
fun GreetingPreview3() {
    ComposeDemoTheme {
       Composable1()
    }
}
