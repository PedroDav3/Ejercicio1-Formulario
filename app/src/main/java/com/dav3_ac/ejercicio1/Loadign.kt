package com.dav3_ac.ejercicio1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dav3_ac.ejercicio1.databinding.ActivityLoadignBinding
import kotlin.concurrent.thread


class Loadign : AppCompatActivity() {

    private lateinit var binding: ActivityLoadignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadignBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_loadign)

        thread {

            Thread.sleep(5000)
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)

            finish()
        }
    }
}