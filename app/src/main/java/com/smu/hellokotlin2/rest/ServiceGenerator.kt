package com.smu.hellokotlin2.rest


import com.google.gson.GsonBuilder
import com.smu.hellokotlin2.model.*
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by sapuser on 9/17/2017.
 */

class ServiceGenerator {
    private val serviceClass: KotlinClient

    private val API_BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"

    init {


        val builder = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))


        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                    //.header("Authorization", basic)
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        // }

        val client = httpClient.build()

        val retrofit = builder.client(client).build()
        this.serviceClass = retrofit.create(KotlinClient::class.java)
    }

    companion object {

        private val httpClient = OkHttpClient.Builder()
        internal var gson = GsonBuilder()
                .setLenient()
                .create()
    }

    fun getAllLeague(callback: Callback<DataSpinnerLeagueResponse>) {
        val call = serviceClass.getAllLeague()
        call.enqueue(callback)
    }

    fun getLastMatch(id: String, callback: Callback<DataLeagueResponse>) {
        val call = serviceClass.getLastMatch(id)
        call.enqueue(callback)
    }

    fun searchEvent(key: String, callback: Callback<DataSearchLeagueResponse>) {
        val call = serviceClass.searchEvent(key)
        call.enqueue(callback)
    }

    fun searchTeam(key: String, callback: Callback<DataTeamLeagueResponse>) {
        val call = serviceClass.searchTeam(key)
        call.enqueue(callback)
    }

    fun searchPlayer(team: String, callback: Callback<DataPlayerLeagueResponse>) {
        val call = serviceClass.searchPlayer(team)
        call.enqueue(callback)
    }

    fun searchPlayerName(nama: String, callback: Callback<DataPlayerLeagueResponse>) {
        val call = serviceClass.searchPlayerName(nama)
        call.enqueue(callback)
    }


    fun getAllTeam(league: String, callback: Callback<DataTeamLeagueResponse>) {
        val call = serviceClass.getTeam(league)
        call.enqueue(callback)
    }


    fun getNextMatch(id: String, callback: Callback<DataLeagueResponse>) {
        val call = serviceClass.getNextMatch(id)
        call.enqueue(callback)
    }

    fun getDetailEvent(id: String, callback: Callback<DataLeagueResponse>) {
        val call = serviceClass.getDetailEvent(id)
        call.enqueue(callback)
    }

    fun getImageTeam(id: String, callback: Callback<DataImageBadgeResponse>) {
        val call = serviceClass.getImageTeam(id)
        call.enqueue(callback)
    }

}
