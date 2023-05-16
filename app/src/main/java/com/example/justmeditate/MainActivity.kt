package com.example.justmeditate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Just Meditate"
        val button: Button = findViewById(R.id.start_button)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, InterfaceTimer::class.java)
            startActivity(intent)
        }

    }
    fun setHourPicker(){
        val hourPicker: NumberPicker = findViewById(R.id.picker_hour)
        hourPicker.minValue = 0
        hourPicker.maxValue = 24
        hourPicker.wrapSelectorWheel = true
    }
    fun setMinutePicker(){
        val minutePicker: NumberPicker = findViewById(R.id.picker_minute)
        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        minutePicker.wrapSelectorWheel = true
    }
    fun setSecondPicker(){
        val secondPicker: NumberPicker = findViewById(R.id.picker_second)
        secondPicker.minValue = 0
        secondPicker.maxValue = 59
        secondPicker.wrapSelectorWheel = true
    }
}