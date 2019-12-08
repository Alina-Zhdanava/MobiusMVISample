package com.android.mobiusmvisample

import android.graphics.Bitmap

data class ScreenModel(
    val isLoading: Boolean = false,
    val data: List<Bitmap>
)