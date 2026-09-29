package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedemo.ui.theme.ComposeDemoTheme


class MyViewModel : ViewModel() {

    var customerCount by mutableStateOf(0)

    var customerName : MutableLiveData<String> = MutableLiveData("")


    fun setName ( name : String  ) {

        customerName.value = name

    }
    fun increaseCount() {

        customerCount++
    }

}
class Chap48WorkingWithViewModelsInCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  MainScreen48(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}



@Composable
fun MainScreen48 ( modifier : Modifier = Modifier ,
                   model : MyViewModel = viewModel()
) {


    MainScreen48VM(model.customerCount) {  model.increaseCount() }

   // val customerName : String by model.customerName.observeAsState("") : Voir livre page 459

}



@Composable
fun MainScreen48VM ( count : Int , addCount : () -> Unit = {} ) {

    Column (  horizontalAlignment = Alignment.CenterHorizontally ,
        modifier = Modifier.fillMaxWidth()) {

        Text(
            "Total customers = $count",
            Modifier.padding(10.dp)
        )

        Button(

            onClick = addCount,

        ) {

            Text(
                text = "Add a Customer"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview28() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen48(modifier = Modifier.padding(innerPadding))
        }
    }
}