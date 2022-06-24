package com.example.customprogressbarjetpackcompose.model

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class ColorItemModel(
    val description: String,
    val color: Color,
    val brush: Brush,
    var selected: Boolean
)
