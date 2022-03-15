package com.example.ui.theme
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//Replace with your font locations
val Roboto = FontFamily.Default

val AppTypography = Typography(
	h6 = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.W400,
		fontSize = 57.sp,
		lineHeight = 64.sp,
		letterSpacing = -0.25.sp
	),
	h5 = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.W400,
		fontSize = 45.sp,
		lineHeight = 52.sp,
		letterSpacing = 0.sp,
	),
	h4 = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.W400,
		fontSize = 36.sp,
		lineHeight = 44.sp,
		letterSpacing = 0.sp,
	),
	h3 = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.W400,
		fontSize = 32.sp,
		lineHeight = 40.sp,
		letterSpacing = 0.sp,
	),
	h2 = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.W400,
		fontSize = 28.sp,
		lineHeight = 36.sp,
		letterSpacing = 0.sp,
	),
	h1 = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.W400,
		fontSize = 24.sp,
		lineHeight = 32.sp,
		letterSpacing = 0.sp,
	),
	subtitle2 = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.W400,
		fontSize = 22.sp,
		lineHeight = 28.sp,
		letterSpacing = 0.sp,
	),
	subtitle1 = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.Medium,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.1.sp,
	),
	body1 = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.Medium,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.1.sp,
	),
	body2 = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.W400,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp,
	),
	caption = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.W400,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.25.sp,
	),
	button = TextStyle(
		fontFamily = Roboto,
		fontWeight = FontWeight.W400,
		fontSize = 12.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.4.sp,
	)
)
