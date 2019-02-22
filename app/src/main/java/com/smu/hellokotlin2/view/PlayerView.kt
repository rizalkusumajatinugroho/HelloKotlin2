package com.smu.hellokotlin2.view

import com.smu.hellokotlin2.model.DataPlayerLeague

/**
 * Created by sapuser on 10/27/2018.
 */
interface PlayerView {
    fun showAllPlayer(data: List<DataPlayerLeague>)
}