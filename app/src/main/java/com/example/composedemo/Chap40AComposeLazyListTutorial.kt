package com.example.composedemo


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.composedemo.ui.theme.ComposeDemoTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext

class Chap40AComposeLazyListTutorial : ComponentActivity() {

    private lateinit var carList : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        carList = resources.getStringArray(R.array.car_array).toList()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MainScreen40( carList = carList ,
                       modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen40 (carList : List<String> , modifier : Modifier = Modifier) {

  //  ImageLoader("Plymouth GTX", modifier)
   // CarListItem("Buick Roadmaster" , modifier = modifier)

    ListPane(modifier, carList)
}

@Composable
fun ListPane (
    modifier :  Modifier = Modifier,
    carList : List<String>
) {

    val context = LocalContext.current
    val onListItemClick = { text : String ->

        Toast.makeText(
            context ,
            text,
            Toast.LENGTH_SHORT
        ).show()

    }

    LazyColumn ( modifier) {

        items ( carList) { model ->
            CarListItem(model , onItemClick = onListItemClick)
        }
    }


}

@Composable
fun CarListItem ( item : String ,
                  onItemClick : (String) -> Unit ,
                  modifier : Modifier = Modifier) {

    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ) ,
        modifier = modifier
            .padding(3.dp)
            .fillMaxWidth()
            .clickable{onItemClick(item)},
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {

        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {

            ImageLoader(item , modifier = Modifier.size(75.dp))
            Spacer(modifier = Modifier.width(8.dp))

            Text(

                text = item,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)

            )
        }

    }

}

@Composable
fun ImageLoader ( item : String , modifier : Modifier = Modifier ) {

    var url = "https://www.payloadbooks.com/book_examples/car_logos/" +item.substringBefore(" ")+
            "_logo.png"

    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = "car image",
        contentScale = ContentScale.Fit,
        modifier = modifier
    )

}

@Preview(showBackground = true, showSystemUi = true )
@Composable
fun GreetingPreview20() {

    val carList : List<String> = listOf(

        "Cadillac Eldorado",
        "Ford Fairlane",
        "Plymouth Fury"
    )
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen40(
               carList = carList ,
                modifier = Modifier.padding(innerPadding))
        }
    }
}