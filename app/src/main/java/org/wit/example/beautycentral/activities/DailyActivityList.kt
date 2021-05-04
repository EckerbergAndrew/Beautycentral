package org.wit.example.beautycentral.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.daily.*
import java.util.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.example.beautycentral.DatabaseHandler
import org.wit.example.beautycentral.R
import org.wit.example.beautycentral.main.MainApp
import org.wit.example.beautycentral.models.ClientModel

class DailyActivityList : AppCompatActivity(), AnkoLogger, BeautyListener {

    lateinit var app: MainApp
    //val addClientReqCode=23

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily)
        app=application as MainApp
        //testing db code
        val databaseHandler:DatabaseHandler= DatabaseHandler(this)
        val clientShow:List<ClientModel> = databaseHandler.viewClients()

        val layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager=layoutManager
        recyclerView?.adapter= ClientAdapter(clientShow,this)
                //app.clients.findAll(),this)

        addClientButton.setOnClickListener(){
            info("add client button pressed")
            //setContentView(R.layout.client_add)
            startActivityForResult(intentFor<AddClientActivity>(),0)
        }
        backBtn.setOnClickListener(){
            info("back button pressed")
            //setContentView(R.layout.activity_main)
            startActivity(intentFor<CalendarActivity>())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addClientButton->startActivityForResult<AddClientActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClientClick(client: ClientModel) {
        startActivityForResult(intentFor<AddClientActivity>().putExtra("client_edit",client),0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data : Intent?) {
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}