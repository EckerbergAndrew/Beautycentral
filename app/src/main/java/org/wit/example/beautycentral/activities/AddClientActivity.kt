package org.wit.example.beautycentral.activities

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.client_add.*
import kotlinx.android.synthetic.main.daily.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.example.beautycentral.R
import org.wit.example.beautycentral.models.ClientModel
import org.wit.example.beautycentral.main.MainApp


class AddClientActivity: AppCompatActivity(),AnkoLogger {

    lateinit var app:MainApp
    var edit=false
    var client=ClientModel()

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_add)
        app=application as MainApp

        if(intent.hasExtra("client_edit")){
            edit=true
            client=intent.extras?.getParcelable<ClientModel>("client_edit")!!
            clientName.setText(client.name)
            clientPhone.setText(client.phoneNo)
            clientService.setText(client.service)
            clientNotes.setText(client.notes)
            btnaddappt.setText(getString(R.string.saveappointment))
        }
        btnaddappt.setOnClickListener {
            client.name = clientName.text.toString()
            client.phoneNo=clientPhone.text.toString()
            client.service=clientService.text.toString()
            client.notes=clientNotes.text.toString()
            if(client.name.isEmpty() || client.phoneNo.isEmpty() || client.service.isEmpty()){
                toast("Please make sure your name, phone number, and desired service" +
                        "are filled out. We can't give you an appointment" +
                        "if we don't know who you are!")
            }
            else{
                if(edit){
                    app.clients.update(client.copy())
                }
                else{
                    app.clients.create(client.copy())
                }
            }
            info { "Add button pressed." }
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }
    }
}