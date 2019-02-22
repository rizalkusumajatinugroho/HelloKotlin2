package com.smu.hellokotlin2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sapuser on 10/27/2018.
 */
class DataSearchLeagueResponse {
    @SerializedName("event")
    @Expose
    val events: List<DataDetailLeague>? = null
}