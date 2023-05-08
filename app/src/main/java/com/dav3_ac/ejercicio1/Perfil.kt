package com.dav3_ac.ejercicio1

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dav3_ac.ejercicio1.databinding.ActivityPerfilBinding

class Perfil : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        val bundle = intent.extras


        val usuarioPerfil: Usuario?

        if (bundle != null) {
            usuarioPerfil= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("usuario", Usuario::class.java)
            } else {
                bundle.getParcelable<Usuario>("usuario")
            }

            if (usuarioPerfil != null) {
                binding.tvNombre.text = getString(R.string.perfil_nombre,usuarioPerfil.nombre)
                binding.tvEdad.text = getString(R.string.perfil_edad,usuarioPerfil.edad)
                binding.tvZodiaco.text = getString(R.string.perfil_zodiaco,usuarioPerfil.zodiaco)
                binding.tvChino.text = getString(R.string.perfil_chino,usuarioPerfil.chino)
                binding.tvCorreo.text = getString(R.string.perfil_correo,usuarioPerfil.correo)
                binding.tvCuenta.text = getString(R.string.perfil_cuenta,usuarioPerfil.cuenta)
                binding.tvCarrera.text = getString(R.string.perfil_carrera,usuarioPerfil.carrera)
                val resourceId = this.resources.getIdentifier(usuarioPerfil.carreraImg, "drawable", this.packageName)
                val resourceIdZodiac = this.resources.getIdentifier(usuarioPerfil.zodiacoImg, "drawable", this.packageName)
                val resourceIdChino = this.resources.getIdentifier(usuarioPerfil.chinoImg, "drawable", this.packageName)
                binding.ivCarrera.setImageResource(resourceId)
                binding.ivZodicaco.setImageResource(resourceIdZodiac)
                binding.ivChino.setImageResource(resourceIdChino)

            }else {
                Log.d("LOGTAG", "error Usuario")
            }
        }
    }

    fun click(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }


}




