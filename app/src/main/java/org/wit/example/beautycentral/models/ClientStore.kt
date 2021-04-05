package org.wit.example.beautycentral.models

interface ClientStore {
    fun findAll(): List<ClientModel>
    fun create(client: ClientModel)
    fun update(client: ClientModel)
}