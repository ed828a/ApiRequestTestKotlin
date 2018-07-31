package com.example.edward.myapplication.data

import android.util.Log
import com.example.edward.myapplication.model.CharactersResponseModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import rx.Observable


/**
 * Created by Edward on 7/31/2018.
 */

class CharactersRemoteDataSource : CharactersDataSource {

    private val api: CharactersDataSource
    private val URL = "http://swapi.co/api/"

    init {
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("YoutubeAPI", it)
        })
        logger.level = HttpLoggingInterceptor.Level.BASIC
        val okHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        this.api = retrofit.create(CharactersDataSource::class.java)
    }

    override fun getCharacters(): Observable<CharactersResponseModel> {
        return this.api.getCharacters()
    }
}