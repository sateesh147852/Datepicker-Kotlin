package com.datepicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*

class DatePickerDialogFragment(val onDateSelected: OnDateSelected) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    private var type: Int = 1
    private var calendar: Calendar = Calendar.getInstance()
    private var simpleDateFormat: SimpleDateFormat = SimpleDateFormat("YYYY/mm/dd")
    private var year : Int = 0
    private var month : Int = 0
    private var day : Int = 0

    init {
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
    }

    /**
     *  1  normal
     *  2  below 18 age
     *  3  current to  future
     *  4  past to current
     */
    public fun setType(type: Int) {
        this.type = type
    }

    interface OnDateSelected {
        fun onDateClicked(milliseconds: Long, year: Int, month: Int, day: Int)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = simpleDateFormat.parse(year.toString() + "/" + month+1 + "/" + dayOfMonth)
        calendar.time = date
        onDateSelected.onDateClicked(calendar.timeInMillis, year,month,dayOfMonth)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = DatePickerDialog(context!!,this,year,month,day)
        when(type){

            2 ->{
                calendar.set(Calendar.YEAR,-16)
                dialog.datePicker.maxDate = calendar.timeInMillis
            }

            3 ->{
                dialog.datePicker.minDate = calendar.timeInMillis
            }

            4 ->{
                dialog.datePicker.maxDate = calendar.timeInMillis
            }
        }
        return dialog
    }
}