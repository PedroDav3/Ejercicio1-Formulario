package com.dav3_ac.ejercicio1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.dav3_ac.ejercicio1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    fun click(view: View) {
        validaciones()
    }

    fun validaciones() {
        val resul = arrayListOf(
            validaNombre(),
            validaApellido(),
            validaCuenta(),
            validaCorreo(),
            validaFecha(),
            validaCarrera()
        )
        if (false in resul) {
            return
        }
        Toast.makeText(this, "Procesando datos", Toast.LENGTH_LONG).show()
    }

    fun validaCuenta(): Boolean {
        return if (binding.etCuenta.text.isNotEmpty()) {
            val cuenta: String = binding.etCuenta.text.toString()
            val minimo = resources.getString(R.string.minCuenta).toInt()
            return if (cuenta.length != minimo) {
                binding.etCuenta.error = resources.getString(R.string.cuentaminimo)
                false
            } else {
                true
            }
        } else {
            binding.etCuenta.error = resources.getString(R.string.valor_requerido)
            false
        }
    }

    fun validaNombre(): Boolean {
        return if (binding.etNombre.text.isEmpty()) {
            binding.etNombre.error = resources.getString(R.string.valor_requerido)
            false
        } else {
            true
        }

    }

    fun validaApellido(): Boolean {
        return if (binding.etApellido.text.isEmpty()) {
            binding.etApellido.error = resources.getString(R.string.valor_requerido)
            false
        } else {
            true
        }
    }

    fun validaCorreo(): Boolean {
        return if (binding.etCorreo.text.isNotEmpty()) {
            val correo: String = binding.etCorreo.text.toString()
            if (!PatternsCompat.EMAIL_ADDRESS.matcher(correo).matches()) {
                binding.etCorreo.error = resources.getString(R.string.correo_invalido)
                false
            } else {
                true
            }
        } else {
            binding.etCorreo.error = resources.getString(R.string.valor_requerido)
            false
        }
    }

    fun validaFecha(): Boolean {
        /* if(binding.etFechaN.text.isNotEmpty()){

         } else {
             binding.etFechaN.error = resources.getString(R.string.valor_requerido)
         }*/
        return if (binding.etFechaN.text.isEmpty()) {
            binding.etFechaN.error = resources.getString(R.string.valor_requerido)
            false
        } else {
            true
        }
    }

    fun validaCarrera(): Boolean {
        return if (binding.etCarrera.text.isEmpty()) {
            binding.etCarrera.error = resources.getString(R.string.valor_requerido)
            false
        } else {
            true
        }
    }

}