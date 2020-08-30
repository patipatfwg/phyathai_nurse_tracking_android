package com.fg.mdp.fwgfacilitiesfinder.clients

import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient


import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

import java.util.concurrent.TimeUnit



    object APIClient {

        val SOSOURL: String = "http://192.168.1.54/"
        val pathURL:String = "phayathai_nurse_tracking_backend/api/"

        var retofit: Retrofit? = null

        var gson = GsonBuilder()
            .setLenient()
            .create()

        val clientTimeout = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val client = Retrofit.Builder()
            .baseUrl(SOSOURL+pathURL)
            //.client(get.getUnsafeOkHttpClient())
            // .client(clientTimeout)

            //.addConverterFactory(ProtoConverterFactory)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiInterface::class.java!!)
    }

