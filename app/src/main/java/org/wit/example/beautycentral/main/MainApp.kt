package org.wit.example.beautycentral.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.example.beautycentral.models.ClientMemStore
import org.wit.example.beautycentral.models.ClientModel

class MainApp : Application(), AnkoLogger{

    //val clients= ArrayList<ClientModel>()
    val clients= ClientMemStore()

    override fun onCreate() {
        super.onCreate()
        info { "Beauty Central Started" }
        //clients.add(ClientModel(1,"Zane","98876868","nails"))
        //clients.add(ClientModel(2, "Aoife","98089090","makeup","sldjfaljfasdlk"))
        //clients.add(ClientModel(3,"Ilze","08893409", "nails","red nails"))
    }
}