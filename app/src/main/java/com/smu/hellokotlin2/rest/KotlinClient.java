package com.smu.hellokotlin2.rest;


import com.smu.hellokotlin2.model.DataImageBadgeResponse;
import com.smu.hellokotlin2.model.DataLeagueResponse;
import com.smu.hellokotlin2.model.DataPlayerLeagueResponse;
import com.smu.hellokotlin2.model.DataSearchLeagueResponse;
import com.smu.hellokotlin2.model.DataSpinnerLeagueResponse;
import com.smu.hellokotlin2.model.DataTeamLeagueResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sapuser on 9/17/2017.
 */

public interface KotlinClient {
    @GET("all_leagues.php")
    Call<DataSpinnerLeagueResponse> getAllLeague();

    @GET("searchevents.php")
    Call<DataSearchLeagueResponse> searchEvent(@Query("e") String e);

    @GET("searchteams.php")
    Call<DataTeamLeagueResponse> searchTeam(@Query("t") String t);


    @GET("searchplayers.php")
    Call<DataPlayerLeagueResponse> searchPlayer(@Query("t") String t);

    @GET("searchplayers.php")
    Call<DataPlayerLeagueResponse> searchPlayerName(@Query("p") String p);

    @GET("search_all_teams.php")
    Call<DataTeamLeagueResponse> getTeam(@Query("l") String league);

    @GET("eventspastleague.php")
    Call<DataLeagueResponse> getLastMatch(@Query("id") String id);

    @GET("eventsnextleague.php")
    Call<DataLeagueResponse> getNextMatch(@Query("id") String id);

    @GET("lookupevent.php")
    Call<DataLeagueResponse> getDetailEvent(@Query("id") String id);

    @GET("lookupteam.php")
    Call<DataImageBadgeResponse> getImageTeam(@Query("id") String id);
}
