package com.example.edward.myapplication.presenter

import com.example.edward.myapplication.model.CharactersResponseModel

/**
 * Created by Edward on 7/31/2018.
 */

interface MainContract {

    interface View {

        fun onFetchDataStarted()

        fun onFetchDataCompleted()

        fun onFetchDataSuccess(charactersResponseModel: CharactersResponseModel)

        fun onFetchDataError(e: Throwable)
    }

    interface Presenter {

        fun loadData()

        fun subscribe()

        fun unsubscribe()

        fun onDestroy()

    }
}