package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class Chap30IntroToFlowRowAndFlowColumn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  MainScreen30(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen30 ( modifier : Modifier = Modifier) {


    FlowRow (


    ) {

        repeat (6){

            MyFlowItem(modifier= Modifier.align(Alignment.Bottom))
        }
    }

    // Une Explication Profonde du code de la lecon 31. Parce que le chap 30 est mieux applique' au chap 31


    /*

    Great question, bro — let's zoom out from the syntax and talk about why this pattern actually matters in real apps.

What FlowRow solves

A normal Row places children in a single horizontal line — if there are too many children to fit, they either get cut off or squished, because a Row never wraps to a new line on its own.

FlowRow fixes exactly that: it lays children out left-to-right, and when it runs out of horizontal space, it automatically wraps to the next line — just like how text wraps in a paragraph. You didn't write any wrapping logic yourself; FlowRow handles all of that measuring/positioning internally.

Where you'll actually use this in real apps

This exact pattern — a container that wraps a variable number of items with unpredictable widths — shows up constantly:

Tag/label systems: think Amazon or eBay showing filter chips like "Free Shipping", "Under $50", "4 Stars & Up" — you don't know in advance how many tags there are or how long each one's text is, so you can't hardcode a fixed Row.
Skill/interest chips: LinkedIn profiles, dating apps ("Hiking", "Photography", "Cooking" chips) — same deal, dynamic list of varying-length items.
Search/filter UIs: e-commerce category filters, recipe app ingredient tags, etc.
Photo/gallery grids with variable-sized thumbnails.
What your specific code demonstrates (the building blocks)
Random/dynamic data generation (items = (1..12).map { ... }) — mimics what would normally come from an API or database: a list of items where you don't know the exact sizes/colors ahead of time. This is realistic — in a real app, that data (tag text, image dimensions, etc.) comes from a server response, not hardcoded values.
A data class to model each item (ItemProperties) — this is exactly how you'd structure real data: e.g., a Tag data class with text: String, backgroundColor: Color, maybe an icon. Bundling related properties into one object instead of separate loose variables is a core Kotlin/Android practice.
forEach to render a dynamic list inside a layout — instead of manually writing TextCell29("1"), TextCell29("2"), TextCell29("3") one by one (like your earlier examples), you're now looping over a list and generating UI for each item. This is the fundamental pattern for any real app screen: a list of products, a list of chat messages, a list of contacts — you always loop over data and emit one composable per item, rather than manually writing out N composables.
Rounded, colored boxes (clip + background) — simulates chip/tag/badge UI elements, which are everywhere in modern app design.
The bigger lesson

What you're really practicing here isn't "how to draw random colored rectangles" — it's the core Compose skill of turning a list of data into a dynamic, responsive UI layout that adapts to content it doesn't know about in advance. That's basically 80% of what real Android app screens are made of: feeds, chip groups, product grids, comment lists, etc. FlowRow is just one more tool (alongside LazyColumn, LazyRow, LazyVerticalGrid which you'll probably hit soon) for handling dynamic collections of UI elements cleanly.

can you break down every part of the code Bro? , like from the data class,to val items ItemsProperties ,FlowRow , items.foreach , Box... what does what and how one impact the other one and how they all come together ?

Alright bro, let's go through this piece by piece, slowly, and see how everything connects.

kotlin
data class ItemProperties(
    val color: Color,
    val width: Dp,
    val height: Dp
)

What it is: A blueprint for describing one item — nothing more. Think of it as a form with 3 blank fields: color, width, height. It doesn't create any actual item yet; it just defines what information every item must carry.

Why it matters: Instead of having three separate loose lists (colors, widths, heights) that you'd have to keep in sync manually (error-prone), you bundle related data into one object per item. This is how real-world models work too — e.g., a Product data class would have name, price, imageUrl all bundled together.

kotlin
val items = (1..12).map {
    ItemProperties(
        width = Random.nextInt(20,100).dp,
        height = Random.nextInt(10,40).dp,
        color = Color(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255), 255)
    )
}

Let's break this into pieces:

(1..12) → creates a range of numbers from 1 to 12. This is just a counting mechanism — you don't actually use the numbers themselves.
.map { ... } → this is a Kotlin function that takes every number in that range (1, 2, 3, ... 12) and transforms each one into something else, based on the code inside the { }. So instead of ending up with a list of numbers [1, 2, 3, ...], you end up with a list of 12 ItemProperties objects.
Inside the map block, for each of those 12 iterations, you create one new ItemProperties object, filling in its width, height, and color fields with random values (random width between 20-100dp, random height between 10-40dp, random RGB color).

Result: items is now a List<ItemProperties> containing 12 objects, each with its own random size and color — like 12 little "recipe cards," each describing one box to be drawn later.

Important: at this point, nothing is drawn yet. This is pure data — no UI has been created. This step is just preparing what to draw.

kotlin
FlowRow(modifier.width(300.dp)) {
    ...
}

This is the container that will hold and arrange whatever UI gets placed inside it.

modifier.width(300.dp) fixes the total width of this container to 300dp. This is important because FlowRow's whole purpose is to wrap items to a new line once it runs out of horizontal space — and it only knows when to wrap because it knows its own fixed width (300dp).
Everything you put inside the { } of FlowRow will be arranged left-to-right, wrapping to a new row whenever the next item doesn't fit within that 300dp width.

At this point, FlowRow is like an empty picture frame — 300dp wide, ready to hold whatever gets placed inside it, but currently empty.

kotlin
items.forEach { properties ->
    Box(
        modifier = Modifier
            .padding(2.dp)
            .width(properties.width)
            .height(30.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(properties.color)
    )
}

This is where your data (the items list) finally gets turned into actual UI.

items.forEach { properties -> ... } → this loops through your list of 12 ItemProperties objects, one at a time. On each loop, the current object is temporarily named properties (you chose that name — could've been called anything, like item).
Inside the loop, for each properties object, you emit one Box composable — a simple rectangle. Each Box:
Reads properties.width → uses that specific item's random width.
Uses a fixed height(30.dp) → all boxes are the same height (interesting: your data class has a height field, but notice you're not actually using properties.height here — you hardcoded 30.dp instead! That's a subtle detail worth fixing if you want the heights to vary too, by changing .height(30.dp) to .height(properties.height)).
Reads properties.color → uses that specific item's random RGB color for the background.
Gets rounded corners (clip(RoundedCornerShape(8.dp))) and a bit of spacing around it (padding(2.dp)).

Since this loop runs 12 times (once per item in the list), it produces 12 separate Box composables, each with its own random width/color — and because all 12 Box calls happen inside the FlowRow { } block, FlowRow treats all 12 of them as its children and arranges them automatically: placing them left to right, wrapping to a new line whenever a box doesn't fit within the 300dp width.

How it all connects — the full picture

Think of it as an assembly line, in this exact order:

data class ItemProperties — defines the shape of a single item's data (like a template/form).
val items = (1..12).map { ... } — actually fills out 12 of those forms with random values, producing a List<ItemProperties>. This is pure data, no visuals yet.
FlowRow(...) — sets up an empty container that knows how wide it is (300dp) and knows how to wrap children onto new lines.
items.forEach { properties -> Box(...) } — walks through the 12 data objects one at a time, and for each one, creates a real visual Box using that object's specific width/color. Because this happens inside FlowRow's { }, all 12 boxes become children of FlowRow, which automatically arranges/wraps them.

The core relationship to understand: the data (items) and the UI (Box) are separate concerns, connected only by the forEach loop. The data doesn't know anything about how it'll be displayed; the Box doesn't know anything about where its data came from — it just receives one properties object per loop iteration and draws itself according to those values. This separation — data model → loop → UI — is the fundamental architecture pattern you'll use for literally every list-based screen in Android development: a list of messages, a list of products, a list of contacts, all follow this exact same "data list → forEach/loop → emit one composable per item" structure.

    * */
}

@Composable
fun MyFlowItem (modifier: Modifier= Modifier) {


    // Voir Livre page 270


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    ComposeDemoTheme {
    Scaffold ( modifier = Modifier.fillMaxSize()){innerPadding->
        MainScreen30(modifier=Modifier.padding(innerPadding))
    }
    }
}