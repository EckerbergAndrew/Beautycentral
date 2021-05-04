package org.wit.example.beautycentral.activities

import android.os.Bundle
import android.content.Intent
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.card_client.*
import kotlinx.android.synthetic.main.client_add.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.example.beautycentral.DatabaseHandler
import org.wit.example.beautycentral.R
import org.wit.example.beautycentral.models.ClientModel
import org.wit.example.beautycentral.main.MainApp


class AddClientActivity: AppCompatActivity(),AnkoLogger{

    lateinit var app:MainApp
    var edit=false
    var client=ClientModel()

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_add)
        app = application as MainApp
        btndel.setVisibility(View.INVISIBLE)
        val databaseHandler:DatabaseHandler= DatabaseHandler(this)

        if(intent.hasExtra("client_edit")) {
            edit = true
            client = intent.extras?.getParcelable<ClientModel>("client_edit")!!
            clientname.setText(client.name)
            clientphone.setText(client.phoneNo)
            clientservice.setText(client.service)
            clientnotes.setText(client.notes)
            btndel.setVisibility(View.VISIBLE)
            btnaddappt.setText(getString(R.string.saveappointment))
        }
        btnaddappt.setOnClickListener(){
            client.name = clientname.text.toString()
            client.phoneNo=clientphone.text.toString()
            client.service= clientservice.text.toString()
            client.notes=clientnotes.text.toString()
            if(client.name.isEmpty() || client.service.isEmpty()){
                val toastie="Please make sure name/service are filled out"
                Toast.makeText(this, toastie, Toast.LENGTH_LONG).show()
            }
            else{
                if(edit){
                    app.clients.update(client.copy())
                    info("save appointment button pressed")
                    val status=databaseHandler.updateClients(ClientModel(client.id,client.name,
                        client.phoneNo,client.service,client.notes))
                    if(status>-1){
                    Toast.makeText(applicationContext,"Client updated",Toast.LENGTH_SHORT)
                    setResult(AppCompatActivity.RESULT_OK)
                    finish()}
                    else{
                        Toast.makeText(applicationContext,"Client not updated",Toast.LENGTH_SHORT)}
                }
                else{
                    app.clients.create(client.copy())
                    val status=databaseHandler.addClientDB(ClientModel(0,name = clientname.toString(),
                        phoneNo = clientphone.toString(),service = clientservice.toString(),
                        notes = clientNotes.toString()))
                    if(status>-1){
                    info("add button pressed")
                    (setResult(AppCompatActivity.RESULT_OK))
                    finish()}
                    else{
                        Toast.makeText(this,"client not added",Toast.LENGTH_SHORT)
                    }
                    }
            }
            //info { "Add button pressed." }
            //setResult(AppCompatActivity.RESULT_OK)
            //finish()
        }
       btncancel.setOnClickListener(){
           info("cancel button pressed, returning to daily screen")
           //startActivity(intentFor<DailyActivityList>())
           setResult(AppCompatActivity.RESULT_CANCELED)
           finish()
       }
        btndel.setOnClickListener(){
        val delalert=AlertDialog.Builder(this)
            delalert.setTitle("Delete client")
            delalert.setMessage("Are you sure you want to delete ${client.name}?")
            delalert.setIcon(R.drawable.ic_dialog_alert)
            delalert.setPositiveButton("Yes"){dialogInterface, which ->

                val status=databaseHandler.deleteClient(
                    ClientModel(client.id,"",
                    "","","","")
                )
                if(status>-1){
                    Toast.makeText(applicationContext,"Client deleted successfully",
                    Toast.LENGTH_SHORT).show()
                }
                dialogInterface.dismiss()
            }
            delalert.setNegativeButton("No"){dialogInterface,which->
                dialogInterface.dismiss()
            }
            val alertDialog:AlertDialog=delalert.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}