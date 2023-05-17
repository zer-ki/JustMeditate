package com.example.justmeditate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker

class MainActivity : AppCompatActivity() {
    lateinit var hourPicker: NumberPicker
    lateinit var minutePicker: NumberPicker
    lateinit var secondPicker: NumberPicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Just Meditate"

        hourPicker = findViewById(R.id.picker_hour)
        hourPicker.minValue = 0
        hourPicker.maxValue = 24
        hourPicker.wrapSelectorWheel = true

        minutePicker = findViewById(R.id.picker_minute)
        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        minutePicker.wrapSelectorWheel = true

        secondPicker = findViewById(R.id.picker_second)
        secondPicker.minValue = 0
        secondPicker.maxValue = 59
        secondPicker.wrapSelectorWheel = true

        val button: Button = findViewById(R.id.button_start)
        button.setOnClickListener {

            val hour = hourPicker.value

            val minute = minutePicker.value

            val second = secondPicker.value

            val intent = Intent(this@MainActivity, InterfaceTimer::class.java)
            intent.putExtra("hour", hour)
            intent.putExtra("minute", minute)
            intent.putExtra("second", second)
            startActivity(intent)
        }
    }
}
