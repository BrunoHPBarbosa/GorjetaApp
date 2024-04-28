package com.example.gorjetaapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRect (private val context: Context,
    private val rect:List<CircleData>): RecyclerView.Adapter<AdapterRect.RectViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RectViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rectatendente, parent, false)
        return RectViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterRect.RectViewHolder, position: Int) {
        holder.bind(rect[position])
    }

    override fun getItemCount(): Int {
        return rect.size
    }
    inner class RectViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val rectAt : ImageView =  itemView.findViewById(R.id.img_atendente)
        private val txtatend : TextView = itemView.findViewById(R.id.nome_atend)

        fun bind(circleData: CircleData) {
            rectAt.setImageResource(circleData.imageRes)
            txtatend.text = circleData.value
        }
      }
    }
