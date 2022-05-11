package com.salmani.mycrypto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.salmani.mycrypto.apiManager.ApiManager
import com.salmani.mycrypto.databinding.ActivityMarketBinding

class MarketActivity : AppCompatActivity() {

    lateinit var binding: ActivityMarketBinding
    val apiManager = ApiManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.layoutToolbar.toolbar.title = "Market"

        initUi()
    }

    private fun initUi() {
        getNewsDate()
    }

    private fun getNewsDate() {

    }
}