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

class Chap50AnOverviewOfAndroidSQLiteDatabases : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  MainScreen50( Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen50( modifier : Modifier = Modifier) {

    // Voir Livre pge 471-475
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview30() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen50( Modifier.padding(innerPadding))
        }
    }
}