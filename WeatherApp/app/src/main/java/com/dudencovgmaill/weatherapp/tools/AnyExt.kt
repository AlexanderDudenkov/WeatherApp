package com.dudencovgmaill.weatherapp.tools

import java.text.SimpleDateFormat
import java.util.*

fun Any?.getDate() = if (this != null)
    SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date()) else ""