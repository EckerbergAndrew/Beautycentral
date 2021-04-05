package org.wit.example.beautycentral.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.example.beautycentral.R

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker=findViewById<DatePicker>(R.id.date_picker)
        val today= Calendar.getInstance()
        datePicker.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH)){
            view,year,month,day->val month=month+1
            val msg = "You selected: $day/$month/$year"

            Toast.makeText(this@MainActivity,msg,Toast.LENGTH_SHORT).show()
        }
        val constraintsBuilder=CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now())
        btnViewSched.setOnClickListener(){
            info("View schedule button pressed")
            setContentView(R.layout.daily)
        }
    }

}