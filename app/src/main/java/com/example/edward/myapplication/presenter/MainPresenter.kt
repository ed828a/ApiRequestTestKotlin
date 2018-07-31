package com.example.edward.myapplication.presenter

import com.example.edward.myapplication.data.CharactersDataSource
import com.example.edward.myapplication.model.CharactersResponseModel
import rx.Observer
import rx.Scheduler
import rx.subscriptions.CompositeSubscription


/**
 * Created by Edward on 7/31/2018.
 */

class MainPresenter (
        private val charactersDataSource: CharactersDataSource,
        private val backgroundScheduler: Scheduler,
        private val mainScheduler: Scheduler,
        private var view: MainContract.View,
        private val subscriptions: CompositeSubscription = CompositeSubscription()
): MainContract.Presenter{
    override fun loadData() {
        view.onFetchDataStarted()
        subscriptions.clear()

        val subscription = charactersDataSource
                .getCharacters()
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe(object : Observer<CharactersResponseModel> {
                    override fun onCompleted() {
                        view.onFetchDataCompleted()
                    }

                    override fun onError(e: Throwable) {
                        view.onFetchDataError(e)
                    }

                    override fun onNext(rootModel: CharactersResponseModel) {
                        view.onFetchDataSuccess(rootModel)
                    }
                })

        subscriptions.add(subscription)

    }

    override fun subscribe() {
        loadData()
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun onDestroy() {

    }
}