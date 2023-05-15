package com.example.justmeditate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.NonCancellable.start

class InterfaceTimer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_interface)
        title="Meditating"
        val button: Button = findViewById(R.id.stop_button)
        button.setOnClickListener {
            val intent = Intent(this@InterfaceTimer, MainActivity::class.java)
            startActivity(intent)
        }
    }
}