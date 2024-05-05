package com.example.gorjetaapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.gorjetaapp.databinding.CalculoScreamBinding


class CalculoScream : AppCompatActivity() {

    private lateinit var binding: CalculoScreamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculoScreamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


    }

}
