package com.smu.hellokotlin2.presenter

import com.smu.hellokotlin2.model.DataImageBadgeResponse
import com.smu.hellokotlin2.model.DataPlayerLeagueResponse
import com.smu.hellokotlin2.model.DataTeamLeagueResponse
import com.smu.hellokotlin2.rest.ServiceGenerator
import com.smu.hellokotlin2.view.MainView
import com.smu.hellokotlin2.view.PlayerView
import com.smu.hellokotlin2.view.TeamView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by sapuser on 10/27/2018.
 */
class TeamPresenter(private val view: MainView, private val teamView: TeamView, private val playerView: PlayerView) {

    fun getAllTeam(league: String) {
        view.showLoading()
        ServiceGenerator().getAllTeam(league, object : Callback<DataTeamLeagueResponse> {

            override fun onResponse(call: Call<DataTeamLeagueResponse>?, response: Response<DataTeamLeagueResponse>?) {
                view.hideLoading()
                if (response != null && response.isSuccessful) {
                    if (response.body().teams != null)
                        teamView.showAllTeam(response.body().teams!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataTeamLeagueResponse>?, t: Throwable?) {
                view.hideLoading()
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })
    }

    fun searchTeam(key: String) {
        view.showLoading()
        ServiceGenerator().searchTeam(key, object : Callback<DataTeamLeagueResponse> {

            override fun onResponse(call: Call<DataTeamLeagueResponse>?, response: Response<DataTeamLeagueResponse>?) {
                view.hideLoading()
                if (response != null && response.isSuccessful) {
                    if (response.body().teams != null)
                        teamView.showAllTeam(response.body().teams!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataTeamLeagueResponse>?, t: Throwable?) {
                view.hideLoading()
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })
    }

    fun searchPlayer(idTeam: String) {
        view.showLoading()
        ServiceGenerator().searchPlayer(idTeam, object : Callback<DataPlayerLeagueResponse> {

            override fun onResponse(call: Call<DataPlayerLeagueResponse>?, response: Response<DataPlayerLeagueResponse>?) {
                view.hideLoading()
                if (response != null && response.isSuccessful) {
                    if (response.body().player != null)
                        playerView.showAllPlayer(response.body().player!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataPlayerLeagueResponse>?, t: Throwable?) {
                view.hideLoading()
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })
    }

    fun getDetailTeam(idTeam: String) {
        view.showLoading()
        ServiceGenerator().getImageTeam(idTeam, object : Callback<DataImageBadgeResponse> {

            override fun onResponse(call: Call<DataImageBadgeResponse>?, response: Response<DataImageBadgeResponse>?) {
                view.hideLoading()
                if (response != null && response.isSuccessful) {
                    if (response.body().events != null)
                        teamView.showTeamDetail(response.body().events!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataImageBadgeResponse>?, t: Throwable?) {
                view.hideLoading()
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })
    }

    fun searchPlayerName(nama: String) {
        view.showLoading()
        ServiceGenerator().searchPlayerName(nama, object : Callback<DataPlayerLeagueResponse> {

            override fun onResponse(call: Call<DataPlayerLeagueResponse>?, response: Response<DataPlayerLeagueResponse>?) {
                view.hideLoading()
                if (response != null && response.isSuccessful) {
                    if (response.body().player != null)
                        playerView.showAllPlayer(response.body().player!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataPlayerLeagueResponse>?, t: Throwable?) {
                view.hideLoading()
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })
    }

}