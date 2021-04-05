package org.wit.example.beautycentral.Activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.client_list.*
import org.wit.example.beautycentral.ClientAdapter
import org.wit.example.beautycentral.R
import org.wit.example.beautycentral.main.MainActivity



class ClientListActivity : AppCompatActivity(){

    lateinit var app: MainActivity

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily)
        app=application as MainActivity

        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter= ClientAdapter(app.clients.findAll(),this)
    }

}
//TODO put the puzzle pieces together
//TODO specifically, list item, daily, clientadd, and tie them to main
//TODO figure out recyclerview and card view stuff
//TODO reference school site and https://material.io/components/text-fields/android#using-text-fields