package com.example.justmeditate

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class InterfaceTimer : AppCompatActivity() {
    lateinit var textView : TextView
    private lateinit var timer: CountDownTimer
    private var timeLeftInMilis by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_interface)
        textView = findViewById(R.id.timer_text)
        title="Meditating"
        val hours = intent.getIntExtra("hour", 0)
        val minutes = intent.getIntExtra("minute", 0)
        val seconds = intent.getIntExtra("second", 0)
        timeLeftInMilis = (hours*3600000+minutes*60000+seconds*1000)
        startTimer()

        val button: Button = findViewById(R.id.button_stop)
        button.setOnClickListener {
            val intent = Intent(this@InterfaceTimer, MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun startTimer(){
        timer = object : CountDownTimer(timeLeftInMilis.toLong(), 1000) {
            override fun onTick(millis: Long) {
                val hms = String.format("%02d:%02d:%02d",
                    (TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(
                        TimeUnit.MILLISECONDS.toDays(
                            millis
                        )
                    )),
                    (TimeUnit.MILLISECONDS.toMinutes(millis) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))),
                    (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millis)
                    ))
                )
                textView.text = (hms)
            }

            override fun onFinish() {
                textView.text = "Finished!"
            }
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