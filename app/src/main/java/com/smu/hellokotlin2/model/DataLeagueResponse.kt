package com.smu.hellokotlin2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by sapuser on 10/22/2018.
 */
data class DataLeagueResponse(
        @SerializedName("events")
        @Expose
        val events: List<DataDetailLeague>? = null
)