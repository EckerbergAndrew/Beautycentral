package org.wit.example.beautycentral.models

interface ClientStore {
    fun findAll(): Set<ClientModel>
    fun create(client: ClientModel)
    fun update(client: ClientModel)
    fun delete(client: ClientModel)
    fun find(selectedDate:String) : Set<ClientModel>
}