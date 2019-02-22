package com.smu.hellokotlin2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sapuser on 10/27/2018.
 */
data class DataPlayerLeague(
        @SerializedName("idPlayer")
        @Expose
        val idPlayer: String? = null,

        @SerializedName("idTeam")
        @Expose
        val idTeam: String? = null,

        @SerializedName("strPlayer")
        @Expose
        val strPlayer: String? = null,

        @SerializedName("strDescriptionEN")
        @Expose
        val strDescriptionEN: String? = null,

        @SerializedName("strThumb")
        @Expose
        val strThumb: String? = null
)