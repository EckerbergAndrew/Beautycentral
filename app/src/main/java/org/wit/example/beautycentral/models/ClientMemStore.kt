package org.wit.example.beautycentral.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId=0L

internal fun getId(): Long {
    return lastId++
}

class ClientMemStore : ClientStore, AnkoLogger {

    private val clients=HashSet<ClientModel>()

    override fun find(selectedDate: String): Set<ClientModel> {
        return clients
    }

    override fun findAll(): Set<ClientModel> {
        return clients
    }

    override fun create(client: ClientModel) {
        client.id= getId()
        clients.add(client)
        logAll()
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

    private fun logAll(){
        clients.forEach{info("$it" )}
    }
}