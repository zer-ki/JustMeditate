package com.example.justmeditate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Just Meditate"
        val button: Button = findViewById(R.id.start_button)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, InterfaceTimer::class:java)
            startActivity(intent)
        }
    }
}