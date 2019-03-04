package com.mg.equation.solver

import kotlin.math.sqrt

data class Result(
    val d: Double,
    var x1: Double?,
    var x2: Double?
)

fun solve(a: Double, b: Double, c: Double): Result {
    val d = b * b - 4 * a * c

    if (d < 0) {
        return Result(d, null, null)
    } else if (d == 0.0) {
        val x = -(b / 2 * a)
        return Result(d, x, x)
    }

    val x1 = (-b + sqrt(d)) / (2 * a)
    val x2 = (-b - sqrt(d)) / (2 * a)

    return Result(d, x1, x2)
}