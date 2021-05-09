package org.wit.example.beautycentral.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.wit.example.beautycentral.R
import org.wit.example.beautycentral.main.MainApp
import org.wit.example.beautycentral.models.ClientModel
import java.text.SimpleDateFormat

class CalendarActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app:MainApp
    var client=ClientModel()
    var caltodaily=12
    var selectedDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app=application as MainApp

        val datePicker=findViewById<DatePicker>(R.id.date_picker)
        val today= Calendar.getInstance()
        val date=datePicker.getDate().toString()
        datePicker.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)){
            view,year,month,day->val month=month+1
             selectedDate = "$day/$month/$year"
        }

        btnViewSched.setOnClickListener(){
            info("View schedule button pressed")
            val intent = intentFor<DailyActivityList>()
            intent.putExtra("date",date)
            intent.putExtra("selectedDate",selectedDate)
            startActivity(intent)
            }
        findSupl.setOnClickListener() {
            info("find supplier button pressed")
            startActivity(intentFor<MapsActivity>())
        }
    }
}
    fun DatePicker.getDate():Date{
        val calendar=Calendar.getInstance()
        calendar.set(year,month,dayOfMonth)
        return calendar.time
    }
