package com.dav3_ac.ejercicio1

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val listener: (day: Int, month: Int, year: Int) -> Unit, private val defaultDate: Calendar):
    DialogFragment(),
    DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Establecer la fecha predeterminada
        val day = defaultDate.get(Calendar.DAY_OF_MONTH)
        val month = defaultDate.get(Calendar.MONTH)
        val year = defaultDate.get(Calendar.YEAR)

        val  picker = DatePickerDialog(activity as Context, this, year,month,day)

        // Establecer 15 años como límite maximo
        val max = Calendar.getInstance(TimeZone.getDefault())
        max.add(Calendar.YEAR, -15)
        val maxDate: Long = max.timeInMillis

        picker.datePicker.maxDate = maxDate

        return picker
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        listener(day,month,year)
    }
}
