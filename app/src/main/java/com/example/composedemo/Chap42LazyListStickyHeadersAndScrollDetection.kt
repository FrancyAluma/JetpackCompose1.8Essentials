package com.example.composedemo

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize



@Parcelize
class CarItem42 (val id : Int) : Parcelable

class Chap42LazyListStickyHeadersAndScrollDetection : ComponentActivity() {

    private lateinit var carList : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        carList = resources.getStringArray(R.array.car_array).toList()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen42(  carList = carList ,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScreen42 (carList : List<String> , modifier : Modifier = Modifier) {

    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<CarItem42>()

    NavigableListDetailPaneScaffold(
        navigator = scaffoldNavigator ,
        listPane = {
            AnimatedPane {
                ListPane42(
                    modifier = modifier ,
                    carList = carList ,
                    scaffoldNavigator = scaffoldNavigator
                )
            }
        } ,
        detailPane = {
            AnimatedPane {
                scaffoldNavigator.currentDestination?.contentKey?.let {
                    DetailPane42(
                        item = it ,
                        carList = carList ,
                        scaffoldNavigator = scaffoldNavigator,
                        modifier = modifier
                    )
                }
            }
        } ,
        extraPane = {
            AnimatedPane {
                ExtraPane42()
            }
        } ,
        defaultBackBehavior = BackNavigationBehavior.PopUntilContentChange
    )

}


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListPane42 (
    modifier: Modifier = Modifier,
    carList: List<String>,
    scaffoldNavigator: ThreePaneScaffoldNavigator<CarItem42>
) {

    val listState = rememberLazyListState()

    val displayButton = remember {
        derivedStateOf { listState.firstVisibleItemIndex > 5 }
    }
    val scope = rememberCoroutineScope()
    val groupedList = carList.groupBy { it.substringBefore(" ") }


    Column (
        modifier = Modifier.fillMaxHeight()
    ) {
        LazyColumn (
            state = listState ,
            modifier = Modifier.weight(1f)
        ) {

            groupedList.forEach { (manufacturer , models) ->

                stickyHeader {
                    Text(
                        text = manufacturer,
                        color = _root_ide_package_.androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier
                            .background(_root_ide_package_.androidx.compose.ui.graphics.Color.Gray)
                            .padding(5.dp)
                            .fillMaxWidth()
                    )
                }

                models.forEach {model ->

                    item{
                        CarListItem42(
                            model ,
                            carList.indexOf(model),
                            onItemClick = {item->
                                scope.launch {
                                    scaffoldNavigator.navigateTo(
                                        ListDetailPaneScaffoldRole.Detail,
                                        item )
                                }

                            }
                        )
                    }

                }
            }



        }

        ScrollButton(
            displayButton ,
            scope,
            listState,
            Modifier.align (Alignment.CenterHorizontally )
        )
    }

}

@Composable
fun ScrollButton (
    displayButton : State<Boolean>,
    scope : CoroutineScope,
    listState : LazyListState,
    modifier : Modifier = Modifier

) {

    AnimatedVisibility(
        visible = displayButton.value ,
        modifier =  modifier
    ) {

        OutlinedButton(
            onClick = {
                scope.launch {
                    listState.scrollToItem(0)
                }
            } ,
            border = BorderStroke (1.dp , _root_ide_package_.androidx.compose.ui.graphics.Color.Gray),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = _root_ide_package_.androidx.compose.ui.graphics.Color.DarkGray
            ) ,
            modifier = modifier.padding(5.dp)
        ) {

            Text(
                text = "Top"
            )
        }
    }

}

@Composable
fun CarListItem42 (item : String,
                   id:Int,
                   onItemClick : (CarItem42) -> Unit,
                   modifier : Modifier = Modifier) {

    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ) ,
        modifier = modifier
            .padding(3.dp)
            .fillMaxWidth()
            .clickable{onItemClick(CarItem42(id))},
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

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun DetailPane42 (

    item: CarItem42,
    carList: List<String>,
    scaffoldNavigator: ThreePaneScaffoldNavigator<CarItem42>,
    modifier: Modifier = Modifier

) {

    val model = carList[item.id]
    val scope = rememberCoroutineScope()

    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.size(32.dp))

            Text(

                text = model,
                style = MaterialTheme.typography.headlineLarge
            )

            ImageLoader( model , modifier = Modifier.fillMaxSize(0.9f))

            Button(
                onClick = {
                    scope.launch {
                        scaffoldNavigator.navigateTo(
                            ListDetailPaneScaffoldRole.Extra,
                            item
                        )
                    }
                }
            ) {
                Text(
                    text = "Extra"
                )
            }
        }

    }
}

@Composable
fun ExtraPane42 ( modifier : Modifier = Modifier) {

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Text (

            text = "Extra Pane" ,
            style = MaterialTheme.typography.headlineLarge
        )
    }

}

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun GreetingPreview22() {

    val carList: List<String> = listOf(

        "Cadillac Eldorado",
        "Ford Fairlane",
        "Plymouth Fury"
    )

    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen42(
                carList = carList,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

