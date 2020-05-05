package com.example.w3d4_coroutinesretrofitexample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.w3d4_coroutinesretrofitexample.network.Ticket
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() ,MainView{
    private val mainPresenter = MainPresenter(this, Interactor())

    private val from = "DEL"
    private val to = "HYD"
    private val flightNumber = "6E-ARI"

    private val tag: String = MainActivity::class.java.simpleName

    //create a new Job
    private val parentJob = Job()

    //create a coroutine context with the job and the dispatcher
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main

    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)

    private var ticketsList: MutableList<Ticket> = mutableListOf()
    private var ticket: MutableList<Ticket> = mutableListOf()

    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TicketsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "$from > $to"


        initRecycler()
    }

    override fun getLatestData() {
        ///launch the coroutine scope
        scope.launch {
            //get latest data from repo
            indeterminateBar.visibility = View.VISIBLE
            ticketsList = mainPresenter.getTickets(from, to)!!
            adapter.updateTickets(ticketsList)
            indeterminateBar.visibility = View.GONE

            for (ticket in ticketsList) {

                ticket.price =mainPresenter.getPrice(ticket.flightNumber, from, to)
                val position = ticketsList.indexOf(ticket)

                adapter.updatePrice( ticket,position)

            }
        }
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.onPause()
        coroutineContext.cancel()

    }

    override fun initRecycler() {
        recyclerView = findViewById(R.id.recycler_view)

        //setting up the adapter
        adapter = TicketsAdapter(applicationContext, ticketsList) { ticket: Ticket ->
            onTicketSelected(ticket)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )

        getLatestData()
    }

    private  fun itemClicked(ticket: Ticket) {
        Toast.makeText(this, "Clicked: ${ticket.airline}", Toast.LENGTH_LONG).show()
    }

    private fun onTicketSelected(ticket: Ticket?) {

        Toast.makeText(this, "Clicked: ${ticket?.flightNumber}", Toast.LENGTH_LONG).show()

    }


}