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
        var hour = hourPicker.value

        minutePicker = findViewById(R.id.picker_minute)
        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        minutePicker.wrapSelectorWheel = true
        var minute = minutePicker.value

        secondPicker = findViewById(R.id.picker_second)
        secondPicker.minValue = 0
        secondPicker.maxValue = 59
        secondPicker.wrapSelectorWheel = true
        var second = secondPicker.value

        val button: Button = findViewById(R.id.start_button)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, InterfaceTimer::class.java)
            intent.putExtra("hour", hour)
            intent.putExtra("minute", minute)
            intent.putExtra("second", second)
            startActivity(intent)
        }
    }
}
