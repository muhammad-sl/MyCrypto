package com.salmani.mycrypto.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salmani.mycrypto.R
import com.salmani.mycrypto.apiManager.BASE_URL_IMAGE
import com.salmani.mycrypto.databinding.ItemRecyclerCoinsBinding
import com.salmani.mycrypto.models.CoinsDate

class MarketAdapter(private var date : ArrayList<CoinsDate.Data> ,
                    private val recyclerCallback: RecyclerCallback)
    : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>()

{
    @SuppressLint("SetTextI18n")
    lateinit var binding: ItemRecyclerCoinsBinding
    inner class MarketViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

        fun bindView(dateCoin :CoinsDate.Data) {
            binding.txtCoinname.text = dateCoin.coinInfo.fullName
            binding.txtPrice.text = dateCoin.dISPLAY.uSD.pRICE

            val taghir = dateCoin.rAW.uSD.cHANGEPCT24HOUR
            if (taghir > 0) {
                binding.txtTaghir.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.colorGain
                    )
                )
                binding.txtTaghir.text =
                    dateCoin.rAW.uSD.cHANGEPCT24HOUR.toString().substring(0, 4) + "%"
            } else if (taghir < 0) {
                binding.txtTaghir.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.colorLoss
                    )
                )
                binding.txtTaghir.text =
                    dateCoin.rAW.uSD.cHANGEPCT24HOUR.toString().substring(0, 5) + "%"
            } else {
                binding.txtTaghir.text = "0%"
            }

            val marketCap = dateCoin.rAW.uSD.mKTCAP / 1000000000
            val indexDot = marketCap.toString().indexOf('.')
            binding.txtMarketCap.text = "$" + marketCap.toString().substring(0 , indexDot + 3) + " B"

            Glide
                .with(itemView)
                .load(BASE_URL_IMAGE + dateCoin.coinInfo.imageUrl)
                .into(binding.imgCoins)


            itemView.setOnClickListener {
                recyclerCallback.OnCoinItemClicked(dateCoin)
            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRecyclerCoinsBinding.inflate(inflater,parent,false)
        return MarketViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.bindView(date[position])
    }

    override fun getItemCount(): Int = date.size

    interface RecyclerCallback{
        fun OnCoinItemClicked(dateCoin: CoinsDate.Data)
    }

}