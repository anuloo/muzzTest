package com.example.muztest.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.muztest.R

// Custom fonts
val AvenirBlackFont = Font(R.font.avenir_black, FontWeight.Black)
val AvenirBookFont = Font(R.font.avenir_book, FontWeight(1))
val AvenirMediumFont = Font(R.font.avenir_medium, FontWeight.Medium)

val AvenirFamily = FontFamily(
    AvenirBlackFont,
    AvenirBookFont,
    AvenirMediumFont,
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)

val AvenirBlack = TextStyle(
    color = TextColor,
    fontFamily = AvenirFamily,
    fontWeight = FontWeight.Black,
    fontSize = 14.sp,
    letterSpacing = -(0.38.sp),
    lineHeight = 12.sp,
)

val AvenirBook = TextStyle(
    color = TextColor,
    fontFamily = AvenirFamily,
    fontWeight = FontWeight(1),
    fontSize = 14.sp,
    letterSpacing = 0.sp,
    lineHeight = 20.sp,
)

val AvenirMedium = TextStyle(
    color = TextColor,
    fontFamily = AvenirFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    letterSpacing = 0.sp,
    lineHeight = 19.sp,
)