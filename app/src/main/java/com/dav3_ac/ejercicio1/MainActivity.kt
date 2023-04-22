package com.dav3_ac.ejercicio1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton = findViewById<Button>(R.id.btnAccion)
        val lamda: (View) -> Unit ={ Log.d("LOGTAG", "HOLA MUNDOO") }

        boton.setOnClickListener(lamda)
    }
}