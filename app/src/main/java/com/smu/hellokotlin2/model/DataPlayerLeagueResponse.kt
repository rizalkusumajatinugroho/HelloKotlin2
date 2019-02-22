package com.smu.hellokotlin2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sapuser on 10/27/2018.
 */
class DataPlayerLeagueResponse {
    @SerializedName("player")
    @Expose
    val player: List<DataPlayerLeague>? = null
}