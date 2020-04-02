package com.example.lerjsonlutadores.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lerjsonlutadores.Lutador
import com.example.lerjsonlutadores.R
import com.example.lerjsonlutadores.activities.DetalhesLutador
import com.squareup.picasso.Picasso

class AdapterLutadores(val vetLutadores: ArrayList<Lutador>, val context: Context) :
    RecyclerView.Adapter<AdapterLutadores.LutadoresViewHolder>() {

    class LutadoresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindFruta(lutador: Lutador) {

            itemView.findViewById<TextView>(R.id.txtNomeLutador).text = lutador.nome
            Picasso.get().load(lutador.image_url).into(itemView.findViewById<ImageView>(
                R.id.imgLutador
            ))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LutadoresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_lutador,parent,false)
        return LutadoresViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return vetLutadores.count()
    }

    override fun onBindViewHolder(holder: LutadoresViewHolder, position: Int) {
        holder.bindFruta(vetLutadores[position])

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context,DetalhesLutador::class.java)

            val bundle = Bundle()
            bundle.putSerializable("lutador", vetLutadores[position])
            i.putExtras(bundle)
            holder.itemView.context.startActivity(i)
        }
    }
}
