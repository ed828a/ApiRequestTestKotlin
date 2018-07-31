package com.example.edward.myapplication

import com.example.edward.myapplication.data.CharactersDataSource
import com.example.edward.myapplication.model.CharactersResponseModel
import com.example.edward.myapplication.presenter.MainContract
import com.example.edward.myapplication.presenter.MainPresenter
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.schedulers.Schedulers


/**
 * Created by Edward on 7/31/2018.
 */

class MainPresenterTest {

    @Mock
    lateinit var  charactersDataSource: CharactersDataSource

    @Mock
    lateinit var view: MainContract.View

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun fetchValidDataShouldLoadIntoView(){
        val charactersResponseModel = CharactersResponseModel(0, null, null, null)

        whenever(charactersDataSource.getCharacters()).thenReturn(Observable.just(charactersResponseModel))

        val mainPresenter = MainPresenter(this.charactersDataSource,
                Schedulers.immediate(),
                Schedulers.immediate(),
                this.view)

        mainPresenter.loadData()

        val inOrder: InOrder = Mockito.inOrder(view)
        inOrder.verify(view, times(1)).onFetchDataStarted()
        inOrder.verify(view, times(1)).onFetchDataSuccess(charactersResponseModel)
        inOrder.verify(view, times(1)).onFetchDataCompleted()
    }

    @Test
    fun fetchErrorShouldReturnErrorToView(){
        val exception = Exception()
        whenever(charactersDataSource.getCharacters())
                .thenReturn(Observable.error(exception))

        val mainPresenter = MainPresenter(this.charactersDataSource,
                Schedulers.immediate(),
                Schedulers.immediate(),
                this.view)

        mainPresenter.loadData()
        val inOrder: InOrder = Mockito.inOrder(view)
        inOrder.verify(view, times(1)).onFetchDataStarted()
        inOrder.verify(view, times(1)).onFetchDataError(exception)
        verify(view, never()).onFetchDataCompleted()

    }
}