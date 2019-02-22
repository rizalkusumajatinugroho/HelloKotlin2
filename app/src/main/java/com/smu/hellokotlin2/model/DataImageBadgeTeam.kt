package com.smu.hellokotlin2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sapuser on 10/23/2018.
 */
data class DataImageBadgeTeam(
        @SerializedName("strTeamBadge")
        @Expose
        val strTeamBadge: String? = null,

        @SerializedName("strDescriptionEN")
        @Expose
        val strDescriptionEN: String? = null,

        @SerializedName("strTeam")
        @Expose
        val strTeam: String? = null
)