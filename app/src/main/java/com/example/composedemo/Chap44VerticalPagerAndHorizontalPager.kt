package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch


val drawables = listOf(R.drawable.ima1,R.drawable.ima2,R.drawable.ima3, R.drawable.ima4, R.drawable.ima5)
class Chap44VerticalPagerAndHorizontalPager : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MainScreen44(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen44( modifier : Modifier = Modifier) {

    CoverPage(modifier)

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoverPage( modifier : Modifier = Modifier) {

    val pagerState = rememberPagerState { drawables.size }
    val coroutineScope = rememberCoroutineScope()

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager (

            state = pagerState,
            modifier = modifier.fillMaxWidth()
        ) {page ->

            Image(
                painter = painterResource(drawables[page]),
                contentDescription = "cover",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
            )
        }

        Row {

            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Previous Page",
                modifier = Modifier
                    .size(75.dp)
                    .clickable{

                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage -1)
                        }
                    }
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Next Page",
                modifier = Modifier
                    .size(75.dp)
                    .clickable{

                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }

            )
        }
    }


}

@Preview(showBackground = true , showSystemUi = true )
@Composable
fun GreetingPreview24() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen44(modifier = Modifier.padding(innerPadding))
        }
    }
}