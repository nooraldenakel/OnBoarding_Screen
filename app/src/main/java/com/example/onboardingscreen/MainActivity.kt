package com.example.onboardingscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onboardingscreen.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingScreenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val items: ArrayList<OnBoardingData> = getOnBoardingData()
                    val pagerState = rememberPagerState(
                        pageCount = items.size,
                        infiniteLoop = false,
                        initialOffscreenLimit = 3,
                        initialPage = 0
                    )
                    OnBoardingPager(
                        item = items,
                        pageState = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

    @ExperimentalPagerApi
    @Composable
    fun OnBoardingPager(
        item: java.util.ArrayList<OnBoardingData>,
        pageState: PagerState,
        modifier: Modifier = Modifier
    ) {
        Box(modifier = modifier) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                HorizontalPager(state = pageState) { page ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(item[page].backgroundColor),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Image(
                            painter = painterResource(id = item[page].image),
                            contentDescription = "",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
        Box(contentAlignment = Alignment.BottomCenter) {
            Card(
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
                elevation = 0.dp,
                shape = BottomCardShape.large
            ) {
                Box {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        PagerIndicator(item = item, currentPage = pageState.currentPage)
                        Text(
                            text = item[pageState.currentPage].mainText,
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, end = 20.dp),
                            color = item[pageState.currentPage].mainColor,
                            fontFamily = Poppins,
                            textAlign = TextAlign.Right,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = item[pageState.currentPage].subText,
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, start = 40.dp, end = 20.dp),
                            color = Color.Gray,
                            fontFamily = Poppins,
                            textAlign = TextAlign.Center,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraLight
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(30.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (pageState.currentPage != 3) {
                                TextButton(onClick = {}) {
                                    Text(
                                        text = "Skip Now",
                                        color = Color(0xFF292D32),
                                        fontFamily = Poppins,
                                        textAlign = TextAlign.Right,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                OutlinedButton(
                                    onClick = { /*TODO*/ },
                                    border = BorderStroke(
                                        14.dp,
                                        item[pageState.currentPage].mainColor
                                    ),
                                    shape = RoundedCornerShape(percent = 50),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = item[pageState.currentPage].mainColor),
                                    modifier = Modifier.size(65.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_right_24),
                                        contentDescription = "",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            } else {
                                Button(
                                    onClick = { /*TODO*/ },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = item[pageState.currentPage].backgroundColor),
                                    contentPadding = PaddingValues(vertical = 12.dp),
                                    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Get Started",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun PagerIndicator(item: ArrayList<OnBoardingData>, currentPage: Int) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            repeat(item.size) {
                Indicator(
                    isSelected = it == currentPage,
                    color = item[it].mainColor
                )
            }
        }
    }

    @Composable
    fun Indicator(isSelected: Boolean, color: Color) {
        val width = animateDpAsState(targetValue = if (isSelected) 40.dp else 10.dp)
        Box(
            modifier = Modifier
                .padding(4.dp)
                .height(10.dp)
                .width(width = width.value)
                .clip(CircleShape)
                .background(if (isSelected) color else Color.Gray.copy(0.5f)),
        )
    }
}