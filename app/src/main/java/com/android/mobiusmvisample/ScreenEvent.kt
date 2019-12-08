package com.android.mobiusmvisample

import android.graphics.Bitmap

sealed class ScreenEvent {

    object UploadData : ScreenEvent()
    object Loading : ScreenEvent()
}