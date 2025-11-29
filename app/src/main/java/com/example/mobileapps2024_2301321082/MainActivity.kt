package com.example.mobileapps2024_2301321082

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobileapps2024_2301321082.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}