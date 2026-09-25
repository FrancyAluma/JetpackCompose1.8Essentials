package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch

class Chap39AComposeRowandColumnList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen39(modifier = Modifier
                        .padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen39 ( modifier : Modifier = Modifier ) {

    ColumnList(modifier)

}

@Composable
fun ColumnList ( modifier: Modifier = Modifier) {


    val scrollState = rememberScrollState()

    val coroutineScope = rememberCoroutineScope()

    Column (
        modifier
    ) {

        RowList39(modifier)

        Row {

            Button( onClick = {

                coroutineScope.launch {
                    scrollState.animateScrollTo(0)
                }
            },
                modifier = Modifier.weight(0.5f)
                    .padding(2.dp)) {
                Text("Top")
            }

            Button(
                onClick = {

                },
               modifier = Modifier.weight(0.5f)
                   .padding(2.dp)
            ) {

                Text(
                    "End"
                )
            }

        }

        Column (modifier.verticalScroll(scrollState)) {

            repeat (500) {

                Text (
                    "List item $it" ,
                    style = MaterialTheme.typography.headlineSmall ,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }

    }


}

@Composable
fun RowList39 ( modifier : Modifier = Modifier) {

    val scrollState = rememberScrollState()

    Row ( modifier.horizontalScroll(scrollState) ) {

        repeat (50) {

            Text(
                "$it",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(5.dp)
            )
        }

    }

}

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun GreetingPreview19() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen39(modifier = Modifier
                .padding(innerPadding))
        }
    }
}