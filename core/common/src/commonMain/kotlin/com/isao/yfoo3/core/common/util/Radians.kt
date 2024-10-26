package com.isao.yfoo3.core.common.util

import kotlin.math.PI

fun toRadians(deg: Double): Double = deg / 180.0 * PI

fun toDegrees(rad: Double): Double = rad * 180.0 / PI