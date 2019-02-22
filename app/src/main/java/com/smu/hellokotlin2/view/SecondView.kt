package com.smu.hellokotlin2.view

import com.smu.hellokotlin2.model.DataImageBadgeTeam

/**
 * Created by sapuser on 10/23/2018.
 */
interface SecondView {
    fun showImage(data: List<DataImageBadgeTeam>, team: String)
}