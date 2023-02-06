package br.senai.sp.jandira.bmicompose.utils

import kotlin.math.pow

fun bmiCalculate(weight: Int, height: Double): Double {
    return weight / (height / 100)
}