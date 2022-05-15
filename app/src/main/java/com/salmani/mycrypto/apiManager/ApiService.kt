package com.salmani.mycrypto.apiManager

import com.salmani.mycrypto.models.CoinsDate
import com.salmani.mycrypto.models.NewsDate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(API_KEY)
    @GET( "v2/news/")
    fun getTopNews(@Query ("sortOrder" ) sortOrder : String = "popular" ) : Call<NewsDate>


    @Headers(API_KEY)
    @GET("top/totalvolfull")
    fun getCoinsDate(
        @Query ("tsym") toSymbol : String = "USD" ,
        @Query ("limit" ) limit : Int = 30
    ) : Call<CoinsDate>




}