package com.salmani.mycrypto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.salmani.mycrypto.R
import com.salmani.mycrypto.databinding.ActivityCoinBinding
import com.salmani.mycrypto.models.CoinsDate

class CoinActivity : AppCompatActivity() {
    lateinit var dateCoin: CoinsDate.Data
    lateinit var binding: ActivityCoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateCoin = intent.getParcelableExtra<CoinsDate.Data>("dateToSend")!!

        binding.layoutToolbar.toolbar.title = dateCoin.coinInfo.fullName

        initUi()
    }

    fun initUi() {
        ShowStatisticsDate()
        ShowChartDate()
        ShowAboutDate()
    }

    private fun ShowAboutDate() {


        binding.layoutStatistics.tvOpenAmount.text = dateCoin.dISPLAY.uSD.oPEN24HOUR.toString()
        binding.layoutStatistics.tvTodayHighAmount.text = dateCoin.dISPLAY.uSD.hIGH24HOUR
        binding.layoutStatistics.tvTodayLowAmount.text = dateCoin.dISPLAY.uSD.lOW24HOUR
        binding.layoutStatistics.tvChangeTodayAmount.text = dateCoin.dISPLAY.uSD.cHANGEDAY
        binding.layoutStatistics.tvVolumeAmount.text = dateCoin.dISPLAY.uSD.tOTALVOLUME24H
        binding.layoutStatistics.tvAlgorithm.text = dateCoin.coinInfo.algorithm
        binding.layoutStatistics.tvMarketCapAmount.text = dateCoin.dISPLAY.uSD.mKTCAP
        binding.layoutStatistics.tvSupplyAmount.text = dateCoin.dISPLAY.uSD.sUPPLY

    }

    private fun ShowChartDate() {

    }

    private fun ShowStatisticsDate() {
    }
}