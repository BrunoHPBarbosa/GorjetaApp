package com.example.gorjetaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.gorjetaapp.databinding.CalculoScreamBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

class DielogCalculo : BottomSheetDialogFragment() {

    private lateinit var circles: MutableList<CircleData>
    private lateinit var adapter: AdapterCircleCalculo
    private lateinit var binding: CalculoScreamBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CalculoScreamBinding.inflate(inflater, container, false)
        val view = binding.root

        setupCarousel()

        circles = mutableListOf(
            CircleData(R.drawable.centercircle, "", false),
            CircleData(R.drawable.centercircle, "€", true)
        )
        adapter = AdapterCircleCalculo(requireContext(), circles)
        binding.vpCircleEdit.adapter = adapter

        var porcentagem: Int = 0

        binding.rbOption1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                porcentagem = 10
            }
        }
        binding.rbOption2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                porcentagem = 15
            }
        }
        binding.rbOption3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                porcentagem = 20
            }
        }
         binding.btnDone1
        .setOnClickListener {
            val totalEditText = binding.total.text
            val numPeopleEditText = binding.numPeople.text

            if (totalEditText?.isEmpty() == true
                || numPeopleEditText?.isEmpty() == true
            ) {
                Snackbar
                    .make(binding.total, "preencha todos os campos", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                val total: Float = totalEditText.toString().toFloat()
                val numPeople: Int = numPeopleEditText.toString().toInt()

                val gorjeta = total / numPeople
                val totaltips = gorjeta * porcentagem / 100
                val resultado = gorjeta + totaltips

                val resultadoFormatado = String.format("%.2f", resultado)


                val valorEdit = view.findViewById<EditText>(R.id.valoredit)
                if (circles.size > 1 && circles[1].hasEditText) {
                    valorEdit.visibility = View.VISIBLE
                } else {
                    valorEdit.visibility = View.INVISIBLE
                }
                circles.firstOrNull()?.let { firstCircle ->
                    val updatedCircle = firstCircle.copy(value = resultadoFormatado)
                    circles[0] = updatedCircle
                    adapter.notifyItemChanged(0)
                }

                val textVisibleTip = binding.textVisibleTip
                val btnAvancarCalculo = binding.btnAvancarCalculo
                val vpCircleEdit = binding.vpCircleEdit
                vpCircleEdit.visibility = View.VISIBLE
                textVisibleTip.visibility = View.VISIBLE
                btnAvancarCalculo.visibility = View.VISIBLE
            }
binding.btnClean.setOnClickListener{
    binding.rbOption1.isChecked = false
    binding.rbOption2.isChecked = false
    binding.rbOption3.isChecked = false
    binding.total.setText("")
    binding.numPeople.setText("")
    val textVisibleTip = binding.textVisibleTip
    val btnAvancarCalculo = binding.btnAvancarCalculo
    val vpCircleEdit = binding.vpCircleEdit
    vpCircleEdit.visibility = View.INVISIBLE
    textVisibleTip.visibility = View.INVISIBLE
    btnAvancarCalculo.visibility = View.INVISIBLE
}

        }
        return view
    }


    private fun setupCarousel() {
        binding.vpCircleEdit.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.35f * kotlin.math.abs(position))
            page.scaleX = 1 - (0.35f * kotlin.math.abs(position))
            page.alpha = 0.20f + (1 - kotlin.math.abs(position))
        }
        binding.vpCircleEdit.setPageTransformer(pageTransformer)
        val itemDecoration = MainActivity.HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.vpCircleEdit.addItemDecoration(itemDecoration)
    }
}
