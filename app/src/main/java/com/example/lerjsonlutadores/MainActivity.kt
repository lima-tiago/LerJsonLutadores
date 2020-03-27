package com.example.lerjsonlutadores

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchJson()
    }

    fun fetchJson() {
        val url = "https://5cd56f8e9c31c600148a99ae.mockapi.io/api/legend/classe/top"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val jsonString = "{\"lutadores\":"+ response.body?.string() + "}"
                println("Sucesso")
                println(jsonString)

                val gson = GsonBuilder().create()

                val lutadores = gson.fromJson(jsonString, Lutadores::class.java)
                println(lutadores.lutadores[0].nome)
//
//                println(lutadores.lutadores[0].nome)

//                val collectionType: Type = object : TypeToken<Lutador>() {}.type
//
//                val lutador:Lutador = gson.fromJson(jsonString, collectionType)
////
//                println(lutador.nome)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Falhou")
            }


        })
    }
}

data class Lutadores(
    @SerializedName("lutadores") val lutadores : List<Lutador>
)

//class temp(val lut:Array<Lutador>)

//class Lutador(val nome:String,val classe:String, val custo: String,val frase:String, val image_url:String)

//data class Lutador (
//
//    @SerializedName("nome") val nome : String,
//    @SerializedName("classe") val classe : String,
//    @SerializedName("custo") val custo : Int,
//    @SerializedName("frase") val frase : String,
//    @SerializedName("image_url") val image_url : String
//)

data class Lutador (

    @SerializedName("nome") val nome : String,
    @SerializedName("classe") val classe : String,
    @SerializedName("custo") val custo : Int,
    @SerializedName("frase") val frase : String,
    @SerializedName("image_url") val image_url : String
)