package com.example.lerjsonlutadores.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lerjsonlutadores.Lutador
import com.example.lerjsonlutadores.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes_lutador.*

class DetalhesLutador : AppCompatActivity() {

    var nome: String = ""
    var classe: String = ""
    var custo: Int = 0
    var frase: String = ""
    var image_url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_lutador)

        geIncommingIntent()
        setFields()
    }

    private fun setFields() {
        Picasso.get().load(image_url).into(imgLutadorGrande)
        txtNome.text = "Nome: $nome"
        txtClasse.text = "Classe: $classe"
        txtCusto.text = "Custo: $custo"
        txtFrase.text = "Frase: $frase"
    }

    private fun geIncommingIntent() {
        if (this.intent.hasExtra("lutador")) {
            val lutador: Lutador = this.intent.getSerializableExtra("lutador") as Lutador

            setDetLutador(lutador)
        }
    }

    private fun setDetLutador(lutador: Lutador) {
        this.nome = lutador.nome
        this.classe = lutador.classe
        this.custo = lutador.custo
        this.frase = lutador.frase
        this.image_url = lutador.image_url
    }
}
