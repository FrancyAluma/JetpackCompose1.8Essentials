package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap34ConstraintLayoutInCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MainScreen34( modifier = Modifier.padding(innerPadding) )
                }
            }
        }
    }
}

@Composable
fun MainScreen34 ( modifier : Modifier = Modifier) {


    // Voir livre Page 300

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview14() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen34( modifier = Modifier.padding(innerPadding) )
        }
    }
}