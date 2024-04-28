package com.example.gorjetaapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CircleAdapter(private val context: Context, private val circles: List<CircleData>) :
    RecyclerView.Adapter<CircleAdapter.CircleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.circulovalor, parent, false)
        return CircleViewHolder(view)
    }

    override fun onBindViewHolder(holder: CircleViewHolder, position: Int) {
        holder.bind(circles[position ])
    }

    override fun getItemCount(): Int {
        return circles.size
    }

    inner class CircleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val circleImageView: ImageView = itemView.findViewById(R.id.circle_center_shape)
        private val valueTextView: TextView = itemView.findViewById(R.id.valor_text)

        fun bind(circleData: CircleData) {
            circleImageView.setImageResource(circleData.imageRes)
            valueTextView.text = circleData.value
        }
    }
}
