package com.example.lab1.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.lab1.R

data class HeroInfo(val name:String, val imagePath: Int, val description: String, val bgColor: Color)

val Heroes = listOf(
    HeroInfo("SpiderMan", R.drawable.spiderman, "It doesn't meter now", SpiderManColor),
    HeroInfo("She-Hulk", R.drawable.she_hulk, "It doesn't meter now", SheHulkColor),
    HeroInfo("Captain America", R.drawable.captain_america, "It doesn't meter now", CaptainAmericaColor),
    HeroInfo("Doctor Strange", R.drawable.doctor_strange, "It doesn't meter now", DoctorStrangeColor),
    HeroInfo("Wolverine",R.drawable.wolverine, "It doesn't meter now", WolverineColor),
    HeroInfo("Iron Man", R.drawable.iron_man, "It doesn't meter now", IronManColor)
)