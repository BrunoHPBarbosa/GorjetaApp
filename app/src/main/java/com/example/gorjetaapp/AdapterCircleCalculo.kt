package com.example.gorjetaapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterCircleCalculo(private val context: Context, private val circles: List<CircleData>) :
    RecyclerView.Adapter<AdapterCircleCalculo.CircleViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CircleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.circulo_scream_edit, parent, false)
        return CircleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return circles.size
    }

    override fun onBindViewHolder(holder: CircleViewHolder, position: Int) {
        holder.bind(circles[position])
    }

    inner class CircleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val circleImg: ImageView = itemView.findViewById(R.id.circle_center_shape_edt)
        private val euroSimble: TextView = itemView.findViewById(R.id.valortext2)
        private val edtTextValor: EditText = itemView.findViewById(R.id.valoredit)

        fun bind(circleData: CircleData) {
            circleImg.setImageResource(circleData.imageRes)
            if (circleData.hasEditText) {
                edtTextValor.visibility = View.VISIBLE
                edtTextValor.hint = circleData.value
                edtTextValor.setText(circleData.value)
                euroSimble.visibility = View.GONE
            } else {
                euroSimble.visibility = View.VISIBLE
                euroSimble.text = circleData.value
                edtTextValor.visibility = View.GONE
            }
        }
    }
}
