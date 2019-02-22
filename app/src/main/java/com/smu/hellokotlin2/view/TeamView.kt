package com.smu.hellokotlin2.view

import com.smu.hellokotlin2.model.DataImageBadgeTeam
import com.smu.hellokotlin2.model.DataTeamLeague

/**
 * Created by sapuser on 10/27/2018.
 */
interface TeamView {
    fun showAllTeam(data: List<DataTeamLeague>)

    fun showTeamDetail(data: List<DataImageBadgeTeam>)
}