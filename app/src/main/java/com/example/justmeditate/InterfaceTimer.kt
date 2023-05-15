package com.example.justmeditate

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.NonCancellable.start
import android.widget.TextView

class InterfaceTimer : AppCompatActivity() {
    lateinit var textView : TextView
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_interface)
        textView = findViewById(R.id.textView)
        title="Meditating"
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                textView.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                textView.text = "Finished!"
            }
        }
        val button: Button = findViewById(R.id.stop_button)
        button.setOnClickListener {
            val intent = Intent(this@InterfaceTimer, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        timer.start()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }
}