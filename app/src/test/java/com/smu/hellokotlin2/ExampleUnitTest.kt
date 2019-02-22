package com.smu.hellokotlin2

import android.content.Context
import com.smu.hellokotlin2.model.DataDetailLeague
import com.smu.hellokotlin2.model.DataLeagueResponse
import com.smu.hellokotlin2.presenter.MainPresenter
import com.smu.hellokotlin2.view.MainView
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Mock
    private
    lateinit var presenter: MainPresenter

    @Mock
    private
    lateinit var mainView : MainView

    @Mock
    private
    lateinit var context : Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(mainView, context)
    }

    @Test
    fun getLastMatch() {
        val teams: MutableList<DataDetailLeague> = mutableListOf()
        val response = DataLeagueResponse(teams)

        val mock = org.mockito.Mockito.mock<MainPresenter>(MainPresenter::class.java)

        `when`(mock.getLastMatchUnitTest("4238")).thenReturn(response)
    }

    @Test
    fun getNextMatch() {
        val teams: MutableList<DataDetailLeague> = mutableListOf()
        val response = DataLeagueResponse(teams)

        val mock = org.mockito.Mockito.mock<MainPresenter>(MainPresenter::class.java)

        `when`(mock.getNextMatchUnitTest("4238")).thenReturn(response)
    }

}
