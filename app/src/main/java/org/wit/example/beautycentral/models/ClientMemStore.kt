package org.wit.example.beautycentral.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId=0

internal fun getId(): Int {
    return lastId++
}

class ClientMemStore : ClientStore, AnkoLogger {

    val clients=ArrayList<ClientModel>()

    override fun findAll(): List<ClientModel> {
        return clients
    }

    override fun create(client: ClientModel) {
        client.id= getId()
        clients.add(client)
        logAll()
    }

    override fun update(client: ClientModel) {
        var foundClient : ClientModel?=clients.find{p -> p.id == client.id}
        if(foundClient != null){
            foundClient.name=client.name
            foundClient.phoneNo=client.phoneNo
            foundClient.service=client.service
            foundClient.notes=client.notes
            logAll()
        }
    }
    fun logAll(){
        clients.forEach{info("${it}" )}
    }
}