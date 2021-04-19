package org.wit.example.beautycentral.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.daily.*
import java.util.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.example.beautycentral.R

class dailyActivity : AppCompatActivity(), AnkoLogger {

    val date=intent.getStringExtra("EXTRA_DATE")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily)


        addClientButton.setOnClickListener(){
            info("add client button pressed")
            setContentView(R.layout.client_add)
        }
    }
}
//dateLabel
//TODO: like, all of this...tomorrow.
//TODO: figure out what i'm doing with all of this