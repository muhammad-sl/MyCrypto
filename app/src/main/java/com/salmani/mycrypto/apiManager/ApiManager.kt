package com.salmani.mycrypto.apiManager

import com.salmani.mycrypto.models.CoinsDate
import com.salmani.mycrypto.models.NewsDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://min-api.cryptocompare.com/data/"
const val API_KEY =
    "authorization: Apikey 95ca1ee1b5c1ad97c89424c5708a7ab31f623dffa599a9a5387ee4a4c8340d79"
const val API_NAME = "Test App"
const val BASE_URL_IMAGE = "https://www.cryptocompare.com"

class ApiManager {
    private val apiservice: ApiService

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiservice = retrofit.create(ApiService::class.java)

    }

    fun getNews(apicallback: ApiCallBack<ArrayList<Pair<String, String>>>) {
        apiservice.getTopNews().enqueue(object : Callback<NewsDate> {
            override fun onResponse(call: Call<NewsDate>, response: Response<NewsDate>) {

                val date = response.body()!!
                val dateToSend: ArrayList<Pair<String, String>> = arrayListOf()
                date.data.forEach {
                    dateToSend.add(Pair(it.title, it.url))

                }
                apicallback.onSucces(dateToSend)


            }

            override fun onFailure(call: Call<NewsDate>, t: Throwable) {
                apicallback.onError(t.message!!)
            }

        })
    }

    fun getCoinsDate(apicallback: ApiCallBack<List<CoinsDate.Data>>){

        apiservice.getCoinsDate().enqueue(object : Callback<CoinsDate> {
            override fun onResponse(call: Call<CoinsDate>, response: Response<CoinsDate>) {
                val date = response.body()!!

                apicallback.onSucces(date.data)

            }

            override fun onFailure(call: Call<CoinsDate>, t: Throwable) {
                apicallback.onError(t.message!!)
            }

        })
    }

    interface ApiCallBack<T> {

        fun onSucces(date: T)
        fun onError(errorMessage: String)

    }

}