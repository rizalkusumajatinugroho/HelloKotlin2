package com.smu.hellokotlin2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sapuser on 10/27/2018.
 */
data class DataSpinnerLeague(
        @SerializedName("idLeague")
        @Expose
        val idLeague: String? = null,

        @SerializedName("strLeague")
        @Expose
        val strLeague: String? = null,

        @SerializedName("strSport")
        @Expose
        val strSport: String? = null,

        @SerializedName("strLeagueAlternate")
        @Expose
        val strLeagueAlternate: String? = null
)