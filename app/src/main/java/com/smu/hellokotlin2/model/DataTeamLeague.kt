package com.smu.hellokotlin2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sapuser on 10/27/2018.
 */
data class DataTeamLeague(
        @SerializedName("idTeam")
        @Expose
        val idTeam: String? = null,

        @SerializedName("strTeam")
        @Expose
        val strTeam: String? = null,

        @SerializedName("strTeamBadge")
        @Expose
        val strTeamBadge: String? = null
)