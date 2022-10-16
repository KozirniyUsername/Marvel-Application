@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.lab1

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.lab1.ui.theme.CustomTheme
import com.example.lab1.ui.theme.HeroInfo
import com.example.lab1.ui.theme.Heroes
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlin.math.absoluteValue

@Composable
fun MakeHeroCard(heroInfo: HeroInfo, modifier:Modifier){
    CustomTheme {
        Surface(color = Color.Transparent, modifier = modifier) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(200.dp))
            Surface(shape = MaterialTheme.shapes.medium) {
                Image(
                    painter = painterResource(id = heroInfo.imagePath),
                    contentDescription = heroInfo.name,
                    modifier = Modifier.size(280.dp, 550.dp),
                    contentScale = ContentScale.Crop,
                ) }
            }
            Row {
                Spacer(modifier = Modifier.width(30.dp))
            
            Column {
                Spacer(modifier = Modifier.height(650.dp))
                Text(
                    text = heroInfo.name,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Left,
                    color = Color.White
                ) }
            }
        }
    }
}

@Composable
fun BackGround(state:LazyListState){
    Surface(color = chooseBackGroundColor(state, Heroes),
        modifier = Modifier.size(1000.dp)) {
        if (isSystemInDarkTheme()) {
            Image(
                painter = painterResource(id = R.drawable.bg_dark),
                contentDescription = "bg",
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.BottomStart
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.bg_light),
                contentDescription = "bg",
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.BottomStart
            )
        }
    }
}

@Composable
fun StaticObjects(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.marvel_logo),
            contentDescription = "marvel logo",
            modifier = Modifier.size(2000.dp, 50.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Choose your hero",
            style = MaterialTheme.typography.subtitle2,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.secondary
        )
    }
}

fun chooseBackGroundColor(state:LazyListState, heroes: List<HeroInfo>) =
    if(state.firstVisibleItemIndex==0 &&
        state.layoutInfo.visibleItemsInfo.size==2) heroes[0].bgColor
else if(state.firstVisibleItemIndex+1<heroes.size) heroes[state.firstVisibleItemIndex+1].bgColor
else Color.Yellow

@SuppressLint("FrequentlyChangedStateReadInComposition")
@OptIn(ExperimentalSnapperApi::class)
@Preview(name = "App", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MarvelApp() {
    CustomTheme {
        val state = rememberLazyListState()

        BackGround(state = state)

        LazyRow(
            state = state,
            flingBehavior = rememberSnapperFlingBehavior(lazyListState = state),

        ) {
            items(Heroes, key = {it.name}) { heroInfo ->
                MakeHeroCard(heroInfo = heroInfo,Modifier.graphicsLayer {
                    val value = 1-(state.layoutInfo.normilizedItemPosition(heroInfo.name).absoluteValue*0.1F)
                    alpha = value
                    scaleX = value
                    scaleY = value
                })
            }
        }


        

        StaticObjects()
    }
}