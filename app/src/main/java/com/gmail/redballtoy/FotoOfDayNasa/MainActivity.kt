package com.gmail.redballtoy.FotoOfDayNasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

//data model into which we will convert json server response
data class DataModel(
    val explanation: String?,
    val hdurl: String?,
    val media_type: String?,
    val title: String?,
    val url: String?
)
