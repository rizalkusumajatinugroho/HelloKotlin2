package com.smu.hellokotlin2.model

/**
 * Created by sapuser on 10/25/2018.
 */
data class DataFavorites(val idEvent: String?, val strHomeTeam: String?, val strAwayTeam: String?, val intHomeScore: String?, val intAwayScore: String?, val dateEvent: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val idEvent: String = "idEvent"
        const val strHomeTeam: String = "strHomeTeam"
        const val strAwayTeam: String = "strAwayTeam"
        const val intHomeScore: String = "intHomeScore"
        const val intAwayScore: String = "intAwayScore"
        const val dateEvent: String = "dateEvent"

    }
}