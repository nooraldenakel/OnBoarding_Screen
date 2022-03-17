package com.example.onboardingscreen

import androidx.compose.ui.graphics.Color
import com.example.onboardingscreen.ui.theme.ColorBlue
import com.example.onboardingscreen.ui.theme.ColorGreen
import com.example.onboardingscreen.ui.theme.ColorYellow

data class OnBoardingData(
    val image: Int,
    val backgroundColor: Color = ColorBlue,
    val mainColor: Color = ColorBlue,
    val mainText: String,
    val subText: String
)

fun getOnBoardingData(): ArrayList<OnBoardingData> {
    val list: ArrayList<OnBoardingData> = ArrayList()
    list.add(
        OnBoardingData(
            image = R.drawable.fruit,
            backgroundColor = Color(0xFF0189C5),
            mainColor = Color(0xFF00B5EA),
            mainText = "Hmmm, Healthy Food",
            subText = "A variety of healthy foods made by the best chefs. Ingredients are easy to find. all delicious flavors can only be found at cookbunda"
        )
    )
    list.add(
        OnBoardingData(
            image = R.drawable.food,
            backgroundColor = ColorYellow,
            mainColor = ColorYellow,
            mainText = "Fresh Drinks, Stay Fresh",
            subText = "Not only food. we provide clear healthy drink options for you. Fresh taste always accompanies you",
        )
    )
    list.add(
        OnBoardingData(
            image = R.drawable.cooking,
            backgroundColor = ColorGreen,
            mainColor = ColorGreen,
            mainText = "Let’s Cooking",
            subText = "Are you ready to make a dish for your friends or family? create an account and cook"
        )
    )
    return list
}
