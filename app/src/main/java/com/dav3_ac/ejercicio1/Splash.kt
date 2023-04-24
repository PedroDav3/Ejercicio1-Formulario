package com.dav3_ac.ejercicio1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dav3_ac.ejercicio1.databinding.ActivitySplashBinding
import kotlin.concurrent.thread

class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        thread {

            Thread.sleep(5000)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }


    }
}