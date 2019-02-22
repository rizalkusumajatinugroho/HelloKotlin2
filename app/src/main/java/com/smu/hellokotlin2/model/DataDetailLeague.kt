package com.smu.hellokotlin2.model

/**
 * Created by sapuser on 10/22/2018.
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataDetailLeague(

        @SerializedName("idEvent")
        @Expose
        val idEvent: String? = null,

        @SerializedName("idSoccerXML")
        @Expose
        val idSoccerXML: String? = null,

        @SerializedName("strEvent")
        @Expose
        val strEvent: String? = null,

        @SerializedName("strFilename")
        @Expose
        val strFilename: String? = null,

        @SerializedName("strSport")
        @Expose
        val strSport: String? = null,

        @SerializedName("idLeague")
        @Expose
        val idLeague: String? = null,

        @SerializedName("strLeague")
        @Expose
        val strLeague: String? = null,

        @SerializedName("strSeason")
        @Expose
        val strSeason: String? = null,

        @SerializedName("strDescriptionEN")
        @Expose
        val strDescriptionEN: String? = null,

        @SerializedName("strHomeTeam")
        @Expose
        val strHomeTeam: String? = null,

        @SerializedName("strAwayTeam")
        @Expose
        val strAwayTeam: String? = null,

        @SerializedName("intHomeScore")
        @Expose
        val intHomeScore: String? = null,

        @SerializedName("intRound")
        @Expose
        val intRound: String? = null,

        @SerializedName("intAwayScore")
        @Expose
        val intAwayScore: String? = null,

        @SerializedName("intSpectators")
        @Expose
        val intSpectators: String? = null,

        @SerializedName("strHomeGoalDetails")
        @Expose
        val strHomeGoalDetails: String? = null,

        @SerializedName("strHomeRedCards")
        @Expose
        val strHomeRedCards: String? = null,

        @SerializedName("strHomeYellowCards")
        @Expose
        val strHomeYellowCards: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        @Expose
        val strHomeLineupGoalkeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        @Expose
        val strHomeLineupDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        @Expose
        val strHomeLineupMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        @Expose
        val strHomeLineupForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        @Expose
        val strHomeLineupSubstitutes: String? = null,

        @SerializedName("strHomeFormation")
        @Expose
        val strHomeFormation: String? = null,

        @SerializedName("strAwayRedCards")
        @Expose
        val strAwayRedCards: String? = null,

        @SerializedName("strAwayYellowCards")
        @Expose
        val strAwayYellowCards: String? = null,

        @SerializedName("strAwayGoalDetails")
        @Expose
        val strAwayGoalDetails: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        @Expose
        val strAwayLineupGoalkeeper: String? = null,

        @SerializedName("strAwayLineupDefense")
        @Expose
        val strAwayLineupDefense: String? = null,

        @SerializedName("strAwayLineupMidfield")
        @Expose
        val strAwayLineupMidfield: String? = null,

        @SerializedName("strAwayLineupForward")
        @Expose
        val strAwayLineupForward: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        @Expose
        val strAwayLineupSubstitutes: String? = null,

        @SerializedName("strAwayFormation")
        @Expose
        val strAwayFormation: String? = null,

        @SerializedName("intHomeShots")
        @Expose
        val intHomeShots: String? = null,

        @SerializedName("intAwayShots")
        @Expose
        val intAwayShots: String? = null,

        @SerializedName("dateEvent")
        @Expose
        val dateEvent: String? = null,

        @SerializedName("strDate")
        @Expose
        val strDate: String? = null,

        @SerializedName("strTime")
        @Expose
        val strTime: String? = null,

        @SerializedName("strTVStation")
        @Expose
        val strTVStation: String? = null,

        @SerializedName("idHomeTeam")
        @Expose
        val idHomeTeam: String? = null,

        @SerializedName("idAwayTeam")
        @Expose
        val idAwayTeam: String? = null,

        @SerializedName("strResult")
        @Expose
        val strResult: String? = null,
        @SerializedName("strCircuit")
        @Expose
        val strCircuit: String? = null,
        @SerializedName("strCountry")
        @Expose
        val strCountry: String? = null,
        @SerializedName("strCity")
        @Expose
        val strCity: String? = null,
        @SerializedName("strPoster")
        @Expose
        val strPoster: String? = null,
        @SerializedName("strFanart")
        @Expose
        val strFanart: String? = null,
        @SerializedName("strThumb")
        @Expose
        val strThumb: String? = null,
        @SerializedName("strBanner")
        @Expose
        val strBanner: String? = null,
        @SerializedName("strMap")
        @Expose
        val strMap: String? = null,

        @SerializedName("strLocked")
        @Expose
        val strLocked: String? = null


)