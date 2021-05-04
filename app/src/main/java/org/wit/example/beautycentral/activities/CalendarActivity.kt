package org.wit.example.beautycentral.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.wit.example.beautycentral.R
import org.wit.example.beautycentral.main.MainApp
import org.wit.example.beautycentral.models.ClientModel

class CalendarActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app:MainApp
    var client=ClientModel()
    var caltodaily=12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app=application as MainApp

        val datePicker=findViewById<DatePicker>(R.id.date_picker)
        val today= Calendar.getInstance()
        datePicker.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)){
            view,year,month,day->val month=month+1
            val msg = "You selected: $day/$month/$year"
            //Toast.makeText(this@CalendarActivity,msg,Toast.LENGTH_SHORT).show()
        }

        /*abstract fun onDateSet(
            view:DatePicker,year:Int,month:Int,dayOfMonth:Int
        ):Unit*/

        btnViewSched.setOnClickListener(){
            info("View schedule button pressed")
            startActivity(intentFor<DailyActivityList>())
            }
        //startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
    }
}