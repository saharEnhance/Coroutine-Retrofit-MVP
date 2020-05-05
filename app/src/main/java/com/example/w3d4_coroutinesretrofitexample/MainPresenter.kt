package com.example.w3d4_coroutinesretrofitexample

import com.example.w3d4_coroutinesretrofitexample.network.Price
import com.example.w3d4_coroutinesretrofitexample.network.Ticket


class MainPresenter(var mainView: MainView?, val mainModel: Interactor) {

    //private var mainView: MainView =mainView

    private val repository: Interactor = Interactor()


     suspend fun getPrice(flightNumber: String, from: String, to: String): Price? {
        return repository.getPrice(flightNumber, from, to)

    }

    suspend fun getTickets(from: String, to: String): MutableList<Ticket>? {
        return repository.getTickets(from, to)!!
    }

    fun onPause() {
        mainView?.onPause()
    }

}


