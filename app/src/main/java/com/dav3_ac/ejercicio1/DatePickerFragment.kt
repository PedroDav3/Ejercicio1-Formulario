package com.dav3_ac.ejercicio1

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

/*class DatePickerFragment(val listener: (day: Int, month: Int, year: Int) -> Unit): DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        listener(day,month,year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar: Calendar = GregorianCalendar(TimeZone.getDefault())
        val day = calendar[Calendar.DAY_OF_MONTH]
        val month = calendar[Calendar.MONTH]
        val year = calendar[Calendar.YEAR]

        val  picker = DatePickerDialog(activity as Context, this, year,month,day)
        return picker
    }

}*/

class DatePickerFragment(val listener: (day: Int, month: Int, year: Int) -> Unit, var defaultDate: Calendar):
    DialogFragment(),
    DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Establecer la fecha predeterminada
        var day = defaultDate.get(Calendar.DAY_OF_MONTH)
        var month = defaultDate.get(Calendar.MONTH)
        var year = defaultDate.get(Calendar.YEAR)
        Log.d("Fecha", defaultDate.toString())



        var  picker = DatePickerDialog(activity as Context, this, year,month,day)

        // Establecer la fecha actual como límite mínimo
        picker.datePicker.maxDate = System.currentTimeMillis() - 1000

        return picker
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        listener(day,month,year)
    }
}
