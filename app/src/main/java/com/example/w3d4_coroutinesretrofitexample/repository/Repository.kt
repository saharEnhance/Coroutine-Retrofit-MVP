package com.example.w3d4_coroutinesretrofitexample.repository

import com.example.w3d4_coroutinesretrofitexample.network.DataModel
import com.example.w3d4_coroutinesretrofitexample.network.ApiInterface
import com.example.w3d4_coroutinesretrofitexample.network.Price
import com.example.w3d4_coroutinesretrofitexample.network.Ticket
import retrofit2.Response

class Repository(private val apiInterface: ApiInterface) : BaseRepository() {
    //get latest news using safe api call
    suspend fun getData(): MutableList<DataModel>? {
        return safeApiCall(
            //await the result of deferred type
            call = { apiInterface.getPhotos().await() },
            error = "Error fetching news"
            //convert to mutable list
        )
    }

    suspend fun getPrice(flightNumber: String, from: String, to: String): Price? {
        return safeApiCall(
            //await the result of deferred type
            call = { apiInterface.getPrice(flightNumber, from, to).await() },
            error = "Error fetching news"
            //convert to mutable list
        )
    }

    suspend fun getTickets(from: String, to: String): MutableList<Ticket>? {
        return safeApiCall(
            //await the result of deferred type
            call = { apiInterface.getTickets(from, to).await() },
            error = "Error fetching news"
            //convert to mutable list
        )
    }

}