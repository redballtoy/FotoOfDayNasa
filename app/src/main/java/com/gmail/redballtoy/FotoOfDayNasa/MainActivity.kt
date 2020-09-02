package com.gmail.redballtoy.FotoOfDayNasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

data class DataModel(
    val explanation: String?,
    val url: String?
)
interface PictureOfTheDayApi {
    GET("planetary/apod")
    fun getPictureOfTheday
}