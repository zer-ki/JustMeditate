package com.example.justmeditate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    lateinit var hourPicker: NumberPicker
    lateinit var minutePicker: NumberPicker
    lateinit var secondPicker: NumberPicker
    lateinit var radioGroup: RadioGroup
    private var selectedRadioButtonId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Just Meditate"

        //define hour picker wheel and its the range
        hourPicker = findViewById(R.id.picker_hour)
        hourPicker.minValue = 0
        hourPicker.maxValue = 24
        hourPicker.wrapSelectorWheel = true

        //define minute picker wheel and its the range
        minutePicker = findViewById(R.id.picker_minute)
        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        minutePicker.wrapSelectorWheel = true

        //define seconds picker wheel and its the range
        secondPicker = findViewById(R.id.picker_second)
        secondPicker.minValue = 0
        secondPicker.maxValue = 59
        secondPicker.wrapSelectorWheel = true

        radioGroup = findViewById(R.id.radio_group)

        //Get selected time values when pressing the start button
        val button: Button = findViewById(R.id.button_start)
        button.setOnClickListener {

            val hour = hourPicker.value

            val minute = minutePicker.value

            val second = secondPicker.value

            selectedRadioButtonId = radioGroup.checkedRadioButtonId

            //Pass selected time values and chosen ambient id to intent
            val intent = Intent(this@MainActivity, InterfaceTimer::class.java)
            intent.putExtra("hour", hour)
            intent.putExtra("minute", minute)
            intent.putExtra("second", second)
            intent.putExtra("selectedRadioButtonId", selectedRadioButtonId)
            startActivity(intent)
        }
    }
}
