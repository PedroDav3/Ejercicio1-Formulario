package com.dav3_ac.ejercicio1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.dav3_ac.ejercicio1.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var defaultDate: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ArrayAdapter.createFromResource(
            this,
            R.array.opciones,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }


        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        binding.etFechaN.setOnClickListener{showDatePickerDialog()}
        defaultDate = GregorianCalendar(TimeZone.getDefault())

    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment({ day, month, year ->
            defaultDate.set(Calendar.YEAR, year)
            defaultDate.set(Calendar.MONTH, month)
            defaultDate.set(Calendar.DAY_OF_MONTH, day)
            onDateSelected(day, month, year)
        },defaultDate)

        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int,month: Int, year: Int){
        binding.etFechaN.setText("$day del $month de $year")
    }


    fun click(view: View) {
        if (validaciones()){
            val intent = Intent(this, Loadign::class.java)
            startActivity(intent)
        }
    }

    fun validaciones(): Boolean {
        val resul = arrayListOf(
            validaNombre(),
            validaApellido(),
            validaCuenta(),
            validaCorreo(),
            validaFecha(),
            validaCarrera(),
        )
        return false !in resul

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
        return if (binding.etFechaN.text.isEmpty()) {
            binding.etFechaN.error = resources.getString(R.string.valor_requerido)
            false
        } else {
            true
        }
    }


    fun validaCarrera(): Boolean {
        return if (binding.spinner.selectedItemPosition == 0) {
            Toast.makeText(this,
                resources.getString(R.string.seleccion_carrera),
                Toast.LENGTH_SHORT)
                .show()
            false
        } else {
            true
        }
    }


}