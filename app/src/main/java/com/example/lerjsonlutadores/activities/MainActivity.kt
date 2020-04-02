package com.example.lerjsonlutadores.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lerjsonlutadores.Lutador
import com.example.lerjsonlutadores.Lutadores
import com.example.lerjsonlutadores.R
import com.example.lerjsonlutadores.adapters.AdapterLutadores
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var lutadores: ArrayList<Lutador> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchJson()
    }

    fun configureAdapter(lutadores: ArrayList<Lutador>) {
        println("debug: configurando adapter on ${Thread.currentThread().name}")
        recyclerLutadores.adapter = AdapterLutadores(lutadores, this)
        recyclerLutadores.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerLutadores.setPadding(6, 6, 6, 6)
    }

    fun fetchJson() {
        val url = "https://5cd56f8e9c31c600148a99ae.mockapi.io/api/legend/classe/top"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = "{\"lutadores\":" + response.body?.string() + "}"
                println("debug:  on ${Thread.currentThread().name}")
                println("debug: ${body} ")
                runOnUiThread {
                    val gson = GsonBuilder().create()
                    setListLutadores(gson.fromJson(body, Lutadores::class.java).lutadores)
                    configureAdapter(lutadores)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun setListLutadores(lutadores: ArrayList<Lutador>) {
        println("debug: setando valores on ${Thread.currentThread().name}")
        this.lutadores = lutadores
    }
}
