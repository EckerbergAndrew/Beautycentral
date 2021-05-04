package org.wit.example.beautycentral.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_client.view.*
import kotlinx.android.synthetic.main.daily.view.*
import org.wit.example.beautycentral.R
import org.wit.example.beautycentral.models.ClientModel

interface BeautyListener {
    fun onClientClick(client: ClientModel)
}

class ClientAdapter constructor(private var clients: List<ClientModel>,
                                private val listener: BeautyListener) : RecyclerView.Adapter<ClientAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_client, parent, false))
    }
    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val client = clients[holder.adapterPosition]
        holder.bind(client,listener)
    }

    override fun getItemCount(): Int = clients.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(client: ClientModel, listener: BeautyListener) {
            itemView.clientName.text= client.name
            itemView.clientPhone.text= client.phoneNo
            itemView.clientService.text=client.service
            itemView.clientNotes.text=client.notes
            itemView.setOnClickListener { listener.onClientClick(client) }
        }
    }
}
