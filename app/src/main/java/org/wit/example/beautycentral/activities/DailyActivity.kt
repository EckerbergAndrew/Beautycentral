package org.wit.example.beautycentral.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.daily.*
import java.util.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.wit.example.beautycentral.R

class DailyActivity : AppCompatActivity(), AnkoLogger {

    val addClientReqCode=23

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily)


        addClientButton.setOnClickListener(){
            info("add client button pressed")
            //setContentView(R.layout.client_add)
            startActivityForResult(intentFor<AddClientActivity>(),addClientReqCode)

        }
        backBtn.setOnClickListener(){
            info("back button pressed")
            //setContentView(R.layout.activity_main)
            startActivity(intentFor<CalendarActivity>())

        }
    }
}
//dateLabel
//TODO: like, all of this...tomorrow.
//TODO: figure out what i'm doing with all of this