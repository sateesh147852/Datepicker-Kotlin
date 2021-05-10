package com.datepicker

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.datepicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialogFragment.OnDateSelected, View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var datePickerDialogFragment: DatePickerDialogFragment
    private val TAG : String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        datePickerDialogFragment = DatePickerDialogFragment(this)
        binding.tvAbove18.setOnClickListener(this)
        binding.tvNormal.setOnClickListener(this)
        binding.tvCurrentToFuture.setOnClickListener(this)
        binding.tvCurrentToPast.setOnClickListener(this)
    }

    override fun onDateClicked(milliseconds: Long, year: Int, month: Int, day: Int) {
        Log.i(TAG, "onDateSelected: $milliseconds  $year    $month  $day")
        Toast.makeText(this, "$year / $month / $day", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.tvNormal -> datePickerDialogFragment.show(supportFragmentManager, "DatePicker")

            R.id.tvAbove18 -> {
                datePickerDialogFragment.show(supportFragmentManager, "DatePicker")
                datePickerDialogFragment.setType(2)
            }

            R.id.tvCurrentToFuture -> {
                datePickerDialogFragment.show(supportFragmentManager, "DatePicker")
                datePickerDialogFragment.setType(3)
            }

            R.id.tvCurrentToPast -> {
                datePickerDialogFragment.show(supportFragmentManager, "DatePicker")
                datePickerDialogFragment.setType(4)
            }
        }
    }
}