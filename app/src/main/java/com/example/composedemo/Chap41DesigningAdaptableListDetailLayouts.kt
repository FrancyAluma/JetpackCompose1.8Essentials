package com.example.composedemo

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize


@Parcelize
class CarItem (val id : Int) : Parcelable

class Chap41DesigningAdaptableListDetailLayouts : ComponentActivity() {

    private lateinit var carList : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        carList = resources.getStringArray(R.array.car_array).toList()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MainScreen41(  carList = carList ,
                       modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScreen41 (carList : List<String> , modifier : Modifier = Modifier) {

    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<CarItem>()

    NavigableListDetailPaneScaffold(
        navigator = scaffoldNavigator ,
        listPane = {
            AnimatedPane {
                ListPane(
                    modifier = modifier ,
                    carList = carList ,
                    scaffoldNavigator = scaffoldNavigator
                )
            }
        } ,
        detailPane = {
            AnimatedPane {
                scaffoldNavigator.currentDestination?.contentKey?.let {
                    DetailPane(
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
                ExtraPane()
            }
        } ,
        defaultBackBehavior = BackNavigationBehavior.PopUntilContentChange
    )

}


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListPane (
    modifier : Modifier = Modifier ,
    carList: List<String>,
    scaffoldNavigator: ThreePaneScaffoldNavigator<CarItem>
) {

    val scope = rememberCoroutineScope()


    LazyColumn (
        modifier
    ) {

        carList.forEachIndexed { id, model ->
            item{
                CarListItem41(
                    model,
                    id,
                    onItemClick = { item ->

                        scope.launch {
                            scaffoldNavigator.navigateTo(
                                ListDetailPaneScaffoldRole.Detail,
                                item
                            )
                        }
                    }
                )
            }
        }

    }

}

@Composable
fun CarListItem41 (item : String,
                   id:Int,
                   onItemClick : (CarItem) -> Unit,
                   modifier : Modifier = Modifier) {

    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ) ,
        modifier = modifier
            .padding(3.dp)
            .fillMaxWidth()
            .clickable{onItemClick(CarItem(id))},
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
fun DetailPane (

    item: CarItem,
    carList: List<String>,
    scaffoldNavigator: ThreePaneScaffoldNavigator<CarItem>,
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
fun ExtraPane ( modifier : Modifier = Modifier) {

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

@Preview(showBackground = true)
@Composable
fun GreetingPreview21() {

    val carList : List<String> = listOf(

        "Cadillac Eldorado",
        "Ford Fairlane",
        "Plymouth Fury"
    )

    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen41(
                carList = carList,
                modifier = Modifier.padding(innerPadding))
        }
    }
}