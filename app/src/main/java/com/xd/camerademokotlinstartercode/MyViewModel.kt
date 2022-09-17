package com.xd.camerademokotlinstartercode
import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel:ViewModel() {
    var userImage = MutableLiveData<Bitmap>()
}