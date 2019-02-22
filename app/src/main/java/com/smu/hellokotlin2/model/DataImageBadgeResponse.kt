package com.smu.hellokotlin2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sapuser on 10/23/2018.
 */
class DataImageBadgeResponse {
    @SerializedName("teams")
    @Expose
    val events: List<DataImageBadgeTeam>? = null
}