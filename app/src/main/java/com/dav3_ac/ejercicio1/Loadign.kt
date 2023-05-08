package com.dav3_ac.ejercicio1

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
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
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val bundle = intent.extras

        val bundlePerfil = Bundle()


        val usuarioPerfil: Usuario?

        if (bundle != null) {


            usuarioPerfil= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("usuario", Usuario::class.java)
            } else {
                bundle.getParcelable<Usuario>("usuario")
            }

            if (usuarioPerfil != null) {
                bundlePerfil.putParcelable("usuario", usuarioPerfil)
            }


        }
        thread {

            Thread.sleep(5000)
            val intent = Intent(this, Perfil::class.java)
            intent.putExtras(bundlePerfil)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        // No hacemos nada aquí para bloquear el botón "Regresar"
    }
}