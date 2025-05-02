package com.bumh3r

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Laucher : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_laucher)

            val btonEnter = findViewById<Button>(R.id.btonEnter)
            val btonExit = findViewById<Button>(R.id.btonExit)

            btonEnter.setOnClickListener {
                val intect = Intent(this, MainActivity::class.java)
                startActivity(intect)
            }
            btonExit.setOnClickListener {
                System.exit(0)
            }
        }
    }