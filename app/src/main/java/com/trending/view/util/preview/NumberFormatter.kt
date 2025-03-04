package com.trending.view.util.preview

fun Long.toReadableFormat() = when {
    this > 1000000 -> "${this / 1000000}M"
    this > 1000 -> "${this / 1000}k"
    else -> this.toString()
}