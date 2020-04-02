package com.example.lerjsonlutadores

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Lutadores(
    @SerializedName("lutadores") val lutadores : ArrayList<Lutador>
)

data class Lutador (
    @SerializedName("nome") val nome : String,
    @SerializedName("classe") val classe : String,
    @SerializedName("custo") val custo : Int,
    @SerializedName("frase") val frase : String,
    @SerializedName("image_url") val image_url : String
) : Serializable