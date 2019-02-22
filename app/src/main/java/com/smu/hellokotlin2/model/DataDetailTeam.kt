package com.smu.hellokotlin2.model

/**
 * Created by sapuser on 10/27/2018.
 */
data class DataDetailTeam(val idTeam: String?, val strTeam: String?, val strBadge: String?) {

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val idTeam: String = "idTeam"
        const val strTeam: String = "strTeam"
        const val strBadge: String = "strBadge"
    }
}