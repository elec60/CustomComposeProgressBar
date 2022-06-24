package com.example.customprogressbarjetpackcompose

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _colorLiveData = MutableLiveData(Brush.horizontalGradient(
        listOf(
            Color.Blue,
            Color.Green
        )
    ))
    val colorLiveData: LiveData<Brush> = _colorLiveData

    fun onColorChanged(brush: Brush){
        _colorLiveData.value = brush
    }
}