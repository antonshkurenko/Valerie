package me.cullycross.valerie.utils

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/2/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

@Suppress("NOTHING_TO_INLINE")
inline fun Int.clamp(min: Int, max: Int): Int {
    return Math.max(min, Math.min(max, this))
}

@Suppress("NOTHING_TO_INLINE")
inline fun Float.clamp(min: Float, max: Float): Float {
    return Math.max(min, Math.min(max, this))
}

@Suppress("NOTHING_TO_INLINE")
inline fun Double.clamp(min: Double, max: Double): Double {
    return Math.max(min, Math.min(max, this))
}

@Suppress("NOTHING_TO_INLINE")
inline fun Long.clamp(min: Long, max: Long): Long {
    return Math.max(min, Math.min(max, this))
}