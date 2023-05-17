package com.example.justmeditate

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import java.util.concurrent.TimeUnit

class InterfaceTimer : AppCompatActivity() {
    lateinit var textView : TextView
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_interface)
        textView = findViewById(R.id.timer_text)
        title="Meditating"
        val hours = intent.getIntExtra("hour", 0)
            timer = object : CountDownTimer(hours*1000.toLong(), 1000) {
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