package com.example.justmeditate

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class InterfaceTimer : AppCompatActivity() {
    lateinit var textView : TextView
    private lateinit var timer: CountDownTimer
    private var timeLeftInMilis : Long = 0
    var ambientId = 0
    private var isTimerRunning : Boolean = false
    private lateinit var pauseButton : Button
    private lateinit var stopButton: Button
    lateinit var mediaPlayerBell: MediaPlayer
    lateinit var mediaPlayerAmbience: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_interface)
        textView = findViewById(R.id.timer_text)
        title="Meditating"

        //Get values from the intent

        val hours = intent.getIntExtra("hour", 0)
        val minutes = intent.getIntExtra("minute", 0)
        val seconds = intent.getIntExtra("second", 0)
        ambientId = intent.getIntExtra("selectedRadioButtonId", 0 )

        //put values into a variable recognized by the timer
        timeLeftInMilis = (hours*3600000+minutes*60000+seconds*1000).toLong()
        startTimer()
        playAmbience()


        pauseButton = findViewById(R.id.button_pause)
        pauseButton.setOnClickListener {
            if(isTimerRunning){
                pauseTimer()
                stopButton.visibility = View.VISIBLE}
            else{
                startTimer()
                if(this@InterfaceTimer::mediaPlayerAmbience.isInitialized) {
                mediaPlayerAmbience.start()}
                pauseButton.text = "Pause"
                stopButton.visibility = View.INVISIBLE}
        }

        stopButton = findViewById(R.id.button_stop)
        stopButton.setOnClickListener {
            if(this@InterfaceTimer::mediaPlayerBell.isInitialized){
                mediaPlayerBell.stop()
            }
            //finish and go back to previous activity
            if(this@InterfaceTimer::mediaPlayerAmbience.isInitialized) {
                mediaPlayerAmbience.stop()
            }
            finish()
        }

    }
    private fun startTimer(){
        isTimerRunning = true
        timer = object : CountDownTimer(timeLeftInMilis, 1000) {
            override fun onTick(millis: Long) {
                timeLeftInMilis = millis
                updateTimerText()
            }
            override fun onFinish() {
                textView.text = "Finished!"
                isTimerRunning = false

                //play the sound when finished
                if(!this@InterfaceTimer::mediaPlayerBell.isInitialized){
                    mediaPlayerBell= MediaPlayer.create(this@InterfaceTimer, R.raw.standing_bell)
                    mediaPlayerBell.start()
                } else {
                    mediaPlayerBell.start()
                }


                stopButton.text = "Go back"
                stopButton.visibility = View.VISIBLE
                pauseButton.visibility = View.INVISIBLE
            }
        }.start()
    }

    private fun pauseTimer(){
            timer.cancel()
            if(this@InterfaceTimer::mediaPlayerAmbience.isInitialized) {
                mediaPlayerAmbience.pause()
            }
            pauseButton.text = "Resume"
            isTimerRunning = false
        }

    fun updateTimerText(){
        //Change the milliseconds to correct format for displaying
        val hms = String.format("%02d:%02d:%02d",
            (TimeUnit.MILLISECONDS.toHours(timeLeftInMilis) - TimeUnit.DAYS.toHours(
                TimeUnit.MILLISECONDS.toDays(
                    timeLeftInMilis
                )
            )),
            (TimeUnit.MILLISECONDS.toMinutes(timeLeftInMilis) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeLeftInMilis))),
            (TimeUnit.MILLISECONDS.toSeconds(timeLeftInMilis) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(timeLeftInMilis)
            ))
        )
        textView.text = (hms)
    }

    private fun playAmbience() {
        if(ambientId==2131231225){
            mediaPlayerAmbience= MediaPlayer.create(this@InterfaceTimer, R.raw.spring_loop) }
        if(ambientId==2131231227){
            mediaPlayerAmbience= MediaPlayer.create(this@InterfaceTimer, R.raw.fireplace_loop) }
        mediaPlayerAmbience.isLooping=true
        mediaPlayerAmbience.start()
        }


    override fun onStart() {
        super.onStart()
        timer.start()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}