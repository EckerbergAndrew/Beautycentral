package org.wit.example.beautycentral.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.daily.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.example.beautycentral.R
import org.wit.example.beautycentral.main.MainApp
import org.wit.example.beautycentral.models.ClientModel

class DailyActivityList : AppCompatActivity(), AnkoLogger, BeautyListener {

    lateinit var app: MainApp
    private var selectedDate: String = ""
    private var clientsListOnSelectedDate = HashSet<ClientModel>()
    //val addClientReqCode=23

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily)
        app = application as MainApp

        val dateDisp = intent.getStringExtra("date")
        selectedDate = intent.getStringExtra("selectedDate").toString()
        if(selectedDate==dateDisp){
            datelabel.text=dateDisp
        }
        else {
            datelabel.text = selectedDate
        }
        //datelabel.text = selectedDate


        val layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager

        loadClients(selectedDate)
        recyclerView?.adapter = ClientAdapter(clientsListOnSelectedDate, this)


        addClientButton.setOnClickListener() {
            info("add client button pressed")
            val intent = intentFor<AddClientActivity>()
            intent.putExtra("selectedDate", selectedDate)
            startActivityForResult(intent, 0)
        }
        backBtn.setOnClickListener() {
            info("back button pressed")
            startActivity(intentFor<CalendarActivity>())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addClientButton -> startActivityForResult<AddClientActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClientClick(client: ClientModel) {
        startActivityForResult(intentFor<AddClientActivity>().putExtra("client_edit", client), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        loadClients(selectedDate)
        recyclerView?.adapter = ClientAdapter(clientsListOnSelectedDate, this)
    }

    private fun loadClients(date : String) {
        clientsListOnSelectedDate = app.clients.find(date) as HashSet<ClientModel>
    }
}