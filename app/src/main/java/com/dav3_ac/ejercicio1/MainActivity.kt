package com.dav3_ac.ejercicio1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.content.pm.ActivityInfo
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.dav3_ac.ejercicio1.databinding.ActivityMainBinding
import java.util.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var defaultDate: Calendar
    private var dia: Int = 0
    private var mes: Int = 0
    private var anio: Int = 0
    private var edadU: Int = 0
    private var cuentaU: String = ""
    private var nombreU: String = ""
    private var zodiacoU: String = ""
    private var chinoU: String = ""
    private var correoU: String = ""
    private var carreraU: String = ""
    private var carreraImgU: String = ""
    private var chinoIm: String = ""
    private var zodiacSignIm: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

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
        binding.tvFecha.setOnClickListener{showDatePickerDialog()}
        defaultDate = GregorianCalendar(TimeZone.getDefault())

    }

    fun click(view: View) {
        if (validaciones()){
            procesaDatos()
            val intent = Intent(this, Loadign::class.java)

            val bundle = Bundle()

            val alumno = Usuario(nombreU,cuentaU,edadU,zodiacoU,zodiacSignIm,chinoU,chinoIm,correoU,carreraImgU,carreraU)

            Log.d("LOGTAGMain", nombreU)

            bundle.putParcelable("usuario", alumno)
            intent.putExtras(bundle)


            startActivity(intent)
        }
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

    private fun onDateSelected(day: Int, month: Int, year: Int){
        binding.etFechaN.error = null
        dia = day
        mes = month+1
        anio = year

        val mesString = when(month+1) {
            1 -> getString(R.string.ene)
            2 -> getString(R.string.feb)
            3 -> getString(R.string.mar)
            4 -> getString(R.string.abr)
            5 -> getString(R.string.may)
            6 -> getString(R.string.jun)
            7 -> getString(R.string.jul)
            8 -> getString(R.string.ago)
            9 -> getString(R.string.sep)
            10 -> getString(R.string.oct)
            11 -> getString(R.string.nov)
            12 -> getString(R.string.dic)
            else -> getString(R.string.valor_requerido)
        }

        val diaString: String = when(day){
            1 -> getString(R.string.st)
            31 -> getString(R.string.st)
            2 ->  getString(R.string.nd)
            22 -> getString(R.string.nd)
            3 -> getString(R.string.rd)
            23 -> getString(R.string.rd)
            else -> getString(R.string.th)
        }

        when (Locale.getDefault().language) {
            "es" -> {
                binding.etFechaN.setText(getString(R.string.fechaNac_Esp,day,mesString,year))
            }
            else -> {
                binding.etFechaN.setText(getString(R.string.fechaNac_Eng,day,mesString,year,diaString))
            }
        }

    }


    private fun validaciones(): Boolean {
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

    private fun validaCuenta(): Boolean {
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

    private fun validaNombre(): Boolean {
        return if (binding.etNombre.text.isEmpty()) {
            binding.etNombre.error = resources.getString(R.string.valor_requerido)
            false
        } else {
            val input = binding.etNombre.text.toString()
            val pattern = Pattern.compile("^[\\p{L} ]+\$") // permitir letras y espacios, incluyendo acentos
            if (pattern.matcher(input).matches()) {
                true
            } else {
                binding.etNombre.error = resources.getString(R.string.nombre_valido)
                false
            }
        }

    }

    private fun validaApellido(): Boolean {
        return if (binding.etApellido.text.isEmpty()) {
            binding.etApellido.error = resources.getString(R.string.valor_requerido)
            false
        } else {
            val input = binding.etApellido.text.toString()
            val pattern = Pattern.compile("^[\\p{L} ]+\$") // permitir letras y espacios, incluyendo acentos
            if (pattern.matcher(input).matches()) {
                true
            } else {
                binding.etApellido.error = resources.getString(R.string.apellido_valido)
                false
            }
        }
    }

    private fun validaCorreo(): Boolean {
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

    private fun validaFecha(): Boolean {
        return if (binding.etFechaN.text.isEmpty()) {
            binding.etFechaN.error = resources.getString(R.string.valor_requerido)
            false
        } else {
            binding.etFechaN.error = null
            true
        }
    }


    private fun validaCarrera(): Boolean {
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

    private fun procesaDatos() {

        val nombreC = getString(R.string.nombre_c, binding.etNombre.text.toString(), binding.etApellido.text.toString())

        var zodiacSign = ""
        var zodiacSignImg = ""

        if ((mes == 3 && dia >= 21) || (mes == 4 && dia <= 20)) {
            zodiacSign = getString(R.string.ari)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.aries)
        } else if ((mes == 4 && dia >= 21) || (mes == 5 && dia <= 21)) {
            zodiacSign = getString(R.string.tau)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.tauro)
        } else if ((mes == 5 && dia >= 22) || (mes == 6 && dia <= 21)) {
            zodiacSign = getString(R.string.gem)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.geminis)
        } else if ((mes == 6 && dia >= 22) || (mes == 7 && dia <= 22)) {
            zodiacSign = getString(R.string.can)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.cancer)
        } else if ((mes == 7 && dia >= 23) || (mes == 8 && dia <= 22)) {
            zodiacSign = getString(R.string.leo)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.leo)
        } else if ((mes == 8 && dia >= 23) || (mes == 9 && dia <= 22)) {
            zodiacSign = getString(R.string.vir)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.virgo)
        } else if ((mes == 9 && dia >= 23) || (mes == 10 && dia <= 22)) {
            zodiacSign = getString(R.string.lib)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.libra)
        } else if ((mes == 10 && dia >= 23) || (mes == 11 && dia <= 21)) {
            zodiacSign = getString(R.string.esc)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.escorpio)
        } else if ((mes == 11 && dia >= 22) || (mes == 12 && dia <= 21)) {
            zodiacSign = getString(R.string.sag)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.sagitario)
        } else if ((mes == 12 && dia >= 22) || (mes == 1 && dia <= 19)) {
            zodiacSign = getString(R.string.cap)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.capricornio)
        } else if ((mes == 1 && dia >= 20) || (mes == 2 && dia <= 18)) {
            zodiacSign = getString(R.string.acu)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.acuario)
        } else if ((mes == 2 && dia >= 19) || (mes == 3 && dia <= 20)) {
            zodiacSign = getString(R.string.pis)
            zodiacSignImg = resources.getResourceEntryName(R.drawable.picis)
        }

        val carreraImg = when(binding.spinner.selectedItemPosition){
            1  -> resources.getResourceEntryName(R.drawable.aereo)
            2  -> resources.getResourceEntryName(R.drawable.civil)
            3  -> resources.getResourceEntryName(R.drawable.geomatica)
            4  -> resources.getResourceEntryName(R.drawable.ambiental)
            5  -> resources.getResourceEntryName(R.drawable.geofisica)
            6  -> resources.getResourceEntryName(R.drawable.geologica)
            7  -> resources.getResourceEntryName(R.drawable.petrolera)
            8  -> resources.getResourceEntryName(R.drawable.minasym)
            9  -> resources.getResourceEntryName(R.drawable.computacion)
            10 -> resources.getResourceEntryName(R.drawable.electricaelectronica)
            11 -> resources.getResourceEntryName(R.drawable.telecom)
            12 -> resources.getResourceEntryName(R.drawable.mecanica)
            13 -> resources.getResourceEntryName(R.drawable.industrial)
            14 -> resources.getResourceEntryName(R.drawable.mecatronica)
            15 -> resources.getResourceEntryName(R.drawable.sistemasbio)
            else -> resources.getResourceEntryName(R.drawable.sincarrera)
        }

        val carrerasArray = resources.getStringArray( R.array.opciones)
        val carrera = carrerasArray[binding.spinner.selectedItemPosition]


        val fechaActual = Calendar.getInstance(TimeZone.getDefault())
        val mesActual = fechaActual.get(Calendar.MONTH)+1

        var edad = fechaActual.get(Calendar.YEAR) - anio
        if(mesActual == mes){
            if(fechaActual.get(Calendar.DAY_OF_MONTH) < dia){
                edad -= 1
            }
        } else if (mesActual < mes) {
            edad -= 1
        }

        val chino = calcularAnoChino(anio,mes,dia)


        chinoIm = chino.posicion
        zodiacSignIm = zodiacSignImg
        edadU = edad
        cuentaU = binding.etCuenta.text.toString()
        nombreU = nombreC
        zodiacoU = zodiacSign
        chinoU = chino.animal
        carreraImgU = carreraImg
        correoU = binding.etCorreo.text.toString()
        carreraU = carrera
    }

    private fun calcularAnoChino(anioGregoriano: Int, mesChino: Int, diaChino: Int): HoroscopoChin {
        val cicloAnimal = resources.getStringArray(R.array.ciclo_animal)
        val offsetAnio =
            resources.getInteger(R.integer.offset_anio) // Año de referencia en el calendario chino (1900)

        var anioChino = (anioGregoriano - offsetAnio) % 12
        if (mesChino < 2 || (mesChino == 2 && diaChino < 4)) {
            if(anioChino == 0){
                anioChino = 11
            } else {
                anioChino -=1
            }
        }

        val posicion = when (anioChino) {
            0 -> resources.getResourceEntryName(R.drawable.rata)
            1 -> resources.getResourceEntryName(R.drawable.buey)
            2 -> resources.getResourceEntryName(R.drawable.tigre)
            3 -> resources.getResourceEntryName(R.drawable.conejo)
            4 -> resources.getResourceEntryName(R.drawable.dragon)
            5 -> resources.getResourceEntryName(R.drawable.serpiente)
            6 -> resources.getResourceEntryName(R.drawable.caballo)
            7 -> resources.getResourceEntryName(R.drawable.cabra)
            8 -> resources.getResourceEntryName(R.drawable.mono)
            9 -> resources.getResourceEntryName(R.drawable.gallo)
            10 -> resources.getResourceEntryName(R.drawable.perro)
            11 -> resources.getResourceEntryName(R.drawable.cerdo)
            else -> resources.getResourceEntryName(R.drawable.chino)

        }

        return HoroscopoChin(cicloAnimal[anioChino], posicion)
    }


    data class HoroscopoChin(val animal: String, val posicion: String)


}