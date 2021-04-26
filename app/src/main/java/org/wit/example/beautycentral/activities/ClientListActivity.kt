package org.wit.example.beautycentral.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_client.view.*
import kotlinx.android.synthetic.main.client_list.*
import kotlinx.android.synthetic.main.daily.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.info
import org.jetbrains.anko.AnkoLogger
import org.wit.example.beautycentral.BeautyListener
import org.wit.example.beautycentral.ClientAdapter
import org.wit.example.beautycentral.R
import org.wit.example.beautycentral.main.MainApp
import org.jetbrains.anko.startActivityForResult
import org.wit.example.beautycentral.models.ClientModel


class ClientListActivity : AppCompatActivity(), BeautyListener, AnkoLogger {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily)
        app=application as MainApp

        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter= ClientAdapter(app.clients.findAll(),this)

        addClientButton.setOnClickListener(){
            info("add client button pressed")
            setContentView(R.layout.client_add)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addClientButton -> startActivityForResult<AddClientActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClientClick(client: ClientModel) {
        startActivityForResult(intentFor<AddClientActivity>().putExtra("client_edit", client),0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

}

/*class ClientAdapter constructor(private var clients:List<ClientModel>) :
    RecyclerView.Adapter<ClientAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientAdapter.MainHolder {
        return ClientAdapter.MainHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.card_client, parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: ClientAdapter.MainHolder, position: Int) {
        val client=clients[holder.adapterPosition]
        holder.bind(client)
    }

    override fun getItemCount(): Int =clients.size

    class MainHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(client: ClientModel){
            itemView.clientName.text=client.name
            itemView.clientPhone.text=client.phoneNo
            itemView.clientService.text=client.service
            itemView.clientNotes.text=client.notes
        }
    }
    }*/
//TODO put the puzzle pieces together
//TODO specifically, list item, daily, clientadd, and tie them to main
//TODO figure out recyclerview and card view stuff
//TODO reference school site and https://material.io/components/text-fields/android#using-text-fields