package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlin.random.Random
import androidx.compose.foundation.lazy.staggeredgrid.items
import kotlin.random.nextInt


data class BoxProperties (
    val color : Color ,
    // val height : Dp ,
    val width : Dp
    
)
class Chap43AComposeLazyStaggeredGridTutorial : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  MainScreen43( modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen43 ( modifier : Modifier = Modifier) {

    val items = ( 1..50).map{

        BoxProperties(
           // height = Random.nextInt(50,200).dp,
            width = Random.nextInt(50,200).dp,
            color = Color(
                Random.nextInt(255),
                Random.nextInt(255),
                Random.nextInt(255),
                255
            )
        )
    }

    //LazyVerticalStaggeredGrid
    LazyHorizontalStaggeredGrid(

       // columns = StaggeredGridCells.Fixed(3),
        rows = StaggeredGridCells.Fixed(3),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
       // horizontalArrangement = Arrangement.spacedBy(8.dp),
        //verticalItemSpacing = 8.dp
        
        horizontalItemSpacing = 8.dp,
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {

        items(items) {values->
            GridItem(properties = values)

        }
    }

}

@Composable
fun GridItem(properties : BoxProperties ) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            //.height(properties.height)
            .width(properties.width)
            .clip(RoundedCornerShape(10.dp))
            .background(properties.color)
    )


}
@Preview(showBackground = true, showSystemUi = true,
    device = "spec: parent=pixel_5,orientation=landscape")
@Composable
fun GreetingPreview23() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen43( modifier = Modifier.padding(innerPadding))
        }
    }
}