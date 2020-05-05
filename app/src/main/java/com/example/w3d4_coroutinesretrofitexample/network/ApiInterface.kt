package com.example.w3d4_coroutinesretrofitexample.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("photos")
    fun getPhotos(): Deferred<Response<MutableList<DataModel>>>


    @GET("airline-tickets.php")
    fun getTickets(
        @Query("from") from: String,
        @Query("to") to: String
    ): Deferred<Response<MutableList<Ticket>>>


    @GET("airline-tickets-price.php")
    fun getPrice(
        @Query("flight_number") flight_number: String,
        @Query("from") from: String,
        @Query("to") to: String
    ): Deferred<Response<Price>>
}