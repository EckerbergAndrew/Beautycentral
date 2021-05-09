package org.wit.example.beautycentral.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.example.beautycentral.models.ClientJSONStore
import org.wit.example.beautycentral.models.ClientMemStore
import org.wit.example.beautycentral.models.ClientModel
import org.wit.example.beautycentral.models.ClientStore

class MainApp : Application(), AnkoLogger{

    //val clients= ArrayList<ClientModel>()
    // val clients= ClientMemStore()
    lateinit var clients:ClientStore
    override fun onCreate() {
        super.onCreate()
        clients=ClientJSONStore(applicationContext)
        info { "Beauty Central Started" }
        //clients.add(ClientModel(1,"Zane","98876868","nails"))
        //clients.add(ClientModel(2, "Aoife","98089090","makeup","sldjfaljfasdlk"))
        //clients.add(ClientModel(3,"Ilze","08893409", "nails","red nails"))
    }
}