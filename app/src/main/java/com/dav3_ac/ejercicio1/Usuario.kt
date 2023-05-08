package com.dav3_ac.ejercicio1
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Usuario(
    var nombre: String?, var cuenta: String?,
    var edad: Int?, var zodiaco: String?, var zodiacoImg: String?,
    var chino: String?, var chinoImg: String?, var correo: String?,
    var carreraImg: String?, var carrera: String?): Parcelable

