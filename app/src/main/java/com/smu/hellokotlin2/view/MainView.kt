package com.smu.hellokotlin2.view

import com.smu.hellokotlin2.model.DataDetailLeague
import com.smu.hellokotlin2.model.DataSpinnerLeague

/**
 * Created by sapuser on 10/22/2018.
 */
interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<DataDetailLeague>)
    fun showSpinnerData(data: List<DataSpinnerLeague>)
    fun showToast(message: String)
}