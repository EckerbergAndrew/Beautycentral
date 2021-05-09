package org.wit.example.beautycentral.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.example.beautycentral.persistencehelper.*
import java.util.*
import kotlin.collections.HashSet

val JSON_FILE="clients.json"
val gsonBuilder=GsonBuilder().setPrettyPrinting().create()
val listType=object :TypeToken<java.util.HashSet<ClientModel>>() {}.type

fun generateRandomID():Long{
    return Random().nextLong()
}
class ClientJSONStore(private val context: Context) : ClientStore,AnkoLogger{
    var clients= HashSet<ClientModel>()

    init {
        if(exists(context, JSON_FILE)){
            deserialize()
        }
    }

    override fun find(selectedDate: String): Set<ClientModel> {

        val clientSet = mutableSetOf<ClientModel>()
        for (i in clients.indices) {
            if (clients.elementAt(i).selectedDate == selectedDate) {
                clientSet.add(clients.elementAt(i))
            }
        }

        return clientSet
    }

    override fun findAll(): Set<ClientModel> {
        return clients
    }

    override fun create(client: ClientModel) {
        client.id= generateRandomID()
        clients.add(client)
        serialize()
    }

    override fun update(client: ClientModel) {
        val foundClient : ClientModel?=clients.find{p -> p.id == client.id}
        if(foundClient != null){
            foundClient.name=client.name
            foundClient.phoneNo=client.phoneNo
            foundClient.service=client.service
            foundClient.notes=client.notes
            logAll()
        }
    }

    override fun delete(client: ClientModel) {
        clients.remove(client)
    }
    private fun serialize(){
        val jsonString= gsonBuilder.toJson(clients, listType)
        write(context, JSON_FILE,jsonString)
    }
    private fun deserialize(){
        val jsonString=read(context, JSON_FILE)
        clients=Gson().fromJson(jsonString, listType)
    }

    private fun logAll(){
        clients.forEach{info("$it" )}
    }
}