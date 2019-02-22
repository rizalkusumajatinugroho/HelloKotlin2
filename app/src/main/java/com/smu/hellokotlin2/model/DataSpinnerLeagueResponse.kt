package com.smu.hellokotlin2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sapuser on 10/27/2018.
 */
class DataSpinnerLeagueResponse {
    @SerializedName("leagues")
    @Expose
    val leagues: List<DataSpinnerLeague>? = null
}