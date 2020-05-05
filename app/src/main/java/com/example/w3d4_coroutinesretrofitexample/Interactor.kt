package com.example.w3d4_coroutinesretrofitexample

import com.example.w3d4_coroutinesretrofitexample.network.ApiClient
import com.example.w3d4_coroutinesretrofitexample.network.Price
import com.example.w3d4_coroutinesretrofitexample.network.Ticket
import com.example.w3d4_coroutinesretrofitexample.repository.Repository

class Interactor() {

    private val repository: Repository = Repository(ApiClient.getClient)

    suspend fun getPrice(flightNumber: String, from: String, to: String): Price? {
        return repository.getPrice(flightNumber, from, to)

    }

    suspend fun getTickets(from: String, to: String): MutableList<Ticket>? {
        return repository.getTickets(from, to)
    }


}


