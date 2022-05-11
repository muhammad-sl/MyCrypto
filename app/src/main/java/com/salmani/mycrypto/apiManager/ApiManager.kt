package com.salmani.mycrypto.apiManager

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://min-api.cryptocompare.com/data/"
const val API_KEY = "authorization : Apikey 95ca1ee1b5c1ad97c89424c5708a7ab31f623dffa599a9a5387ee4a4c8340d79"
const val API_NAME = "Test App"
const val BASE_URL_IMAGE ="https://www.cryptocompare.com"

class ApiManager {
    private val apiservice: ApiService

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiservice = retrofit.create(ApiService::class.java)

    }

    interface ApiCallBack<T> {

        fun onSucces(date : T)
        fun onError(errorMessage : String)
        
    }

}