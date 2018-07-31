package com.example.edward.myapplication.data

import com.example.edward.myapplication.model.CharactersResponseModel
import retrofit2.http.GET
import rx.Observable


/**
 * Created by Edward on 7/31/2018.
 */

interface CharactersDataSource {
    @GET("people/")
    fun getCharacters(): Observable<CharactersResponseModel>
}