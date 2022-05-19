package com.salmani.mycrypto.activities

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.salmani.mycrypto.adapter.MarketAdapter
import com.salmani.mycrypto.apiManager.ApiManager
import com.salmani.mycrypto.databinding.ActivityMarketBinding
import com.salmani.mycrypto.models.CoinsDate

class MarketActivity : AppCompatActivity(), MarketAdapter.RecyclerCallback {

    lateinit var binding: ActivityMarketBinding
    lateinit var dateNews: ArrayList<Pair<String, String>>
    lateinit var myAapter: MarketAdapter
    val apiManager = ApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.layoutToolbar.toolbar.title = "Market"

        binding.layoutWatchlist.btnShowMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.livecoinwatch.com/"))
            startActivity(intent)
        }

        binding.swipeRefresh.setOnRefreshListener {

            initUi()

            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeRefresh.isRefreshing = false
            }, 1500)

        }

        initUi()
    }

    private fun initUi() {
        getNewsFromApi()
        getCoinsDateFromApi()

    }

    private fun getNewsFromApi() {
        apiManager.getNews(object : ApiManager.ApiCallBack<ArrayList<Pair<String, String>>> {
            override fun onSucces(date: ArrayList<Pair<String, String>>) {
                dateNews = date
                refreshNews()
            }

            override fun onError(errorMessage: String) {
//                Toast.makeText(this@MarketActivity,"Problem is : $errorMessage" , Toast.LENGTH_SHORT).show()
                Log.v("testLog", errorMessage)
            }
        })

    }

    private fun refreshNews() {
        val randomeAccess = (0..49).random()
        binding.layoutNews.txtNews.text = dateNews[randomeAccess].first
        binding.layoutNews.iconNews.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dateNews[randomeAccess].second))
            startActivity(intent)
        }
        binding.layoutNews.txtNews.setOnClickListener {
            refreshNews()
        }
    }

    private fun getCoinsDateFromApi() {
        apiManager.getCoinsDate(object : ApiManager.ApiCallBack<List<CoinsDate.Data>> {
            override fun onSucces(date: List<CoinsDate.Data>) {
                binding.lottieLoading.visibility = View.GONE
                showDateInRecycler(date)
            }

            override fun onError(errorMessage: String) {
                Log.v("testLog", errorMessage)
                binding.lottieLoading.visibility = View.GONE


            }

        })
    }

    private fun showDateInRecycler(date: List<CoinsDate.Data>) {
        myAapter = MarketAdapter(ArrayList(date), this)
        binding.layoutWatchlist.recyclerView.adapter = myAapter
        binding.layoutWatchlist.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun OnCoinItemClicked(dateCoin: CoinsDate.Data) {
        val intent = Intent(this, CoinActivity::class.java)
        intent.putExtra("dateToSend", dateCoin)
        startActivity(intent)
    }






}