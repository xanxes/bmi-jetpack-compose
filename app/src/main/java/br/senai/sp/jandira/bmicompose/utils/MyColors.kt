package br.senai.sp.jandira.bmicompose.utils

import androidx.compose.ui.graphics.Color

fun getColor(bmiScoreState: Double): Color {
    if (bmiScoreState <= 18.5) {
        return Color.Red
    }

    else if (bmiScoreState > 18.5 && bmiScoreState < 25) {
        return Color.Green
    }

    else if (bmiScoreState > 25 && bmiScoreState < 30){
        return Color.Yellow
    }

    else {
        return Color.Red
    }
}