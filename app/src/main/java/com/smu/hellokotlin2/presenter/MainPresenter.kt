package com.smu.hellokotlin2.presenter

import android.content.Context
import android.util.Log
import com.smu.hellokotlin2.model.DataLeagueResponse
import com.smu.hellokotlin2.model.DataSearchLeagueResponse
import com.smu.hellokotlin2.model.DataSpinnerLeagueResponse
import com.smu.hellokotlin2.rest.ServiceGenerator
import com.smu.hellokotlin2.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by sapuser on 10/22/2018.
 */
class MainPresenter(private val view: MainView, private val context: Context) {
    lateinit var responseLokal: DataLeagueResponse

    fun getLastMatch(idEvent: String) {
        view.showLoading()
        ServiceGenerator().getLastMatch(idEvent, object : Callback<DataLeagueResponse> {

            override fun onResponse(call: Call<DataLeagueResponse>?, response: Response<DataLeagueResponse>?) {
                view.hideLoading()
                Log.d("debug_rizal", "response : " + response!!.code())
                if (response.isSuccessful) {
                    if (response.body().events != null)
                        view.showTeamList(response.body().events!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataLeagueResponse>?, t: Throwable?) {
                view.hideLoading()
                Log.d("debug_rizal", "failed : " + t.toString())
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })
    }

    fun searchEvent(key: String) {
        view.showLoading()
        ServiceGenerator().searchEvent(key, object : Callback<DataSearchLeagueResponse> {

            override fun onResponse(call: Call<DataSearchLeagueResponse>?, response: Response<DataSearchLeagueResponse>?) {
                view.hideLoading()
                Log.d("debug_rizal", "response : " + response!!.code())
                if (response.isSuccessful) {
                    if (response.body().events != null)
                        view.showTeamList(response.body().events!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataSearchLeagueResponse>?, t: Throwable?) {
                view.hideLoading()
                Log.d("debug_rizal", "failed : " + t.toString())
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })
    }


    fun getAllLeague() {
        view.showLoading()
        ServiceGenerator().getAllLeague(object : Callback<DataSpinnerLeagueResponse> {

            override fun onResponse(call: Call<DataSpinnerLeagueResponse>?, response: Response<DataSpinnerLeagueResponse>?) {
                view.hideLoading()
                Log.d("debug_rizal", "response : " + response!!.code())
                if (response.isSuccessful) {
                    if (response.body().leagues != null)
                        view.showSpinnerData(response.body().leagues!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataSpinnerLeagueResponse>?, t: Throwable?) {
                view.hideLoading()
                Log.d("debug_rizal", "failed : " + t.toString())
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })
    }

    fun getNextMatch(idEvent: String) {
        view.showLoading()
        ServiceGenerator().getNextMatch(idEvent, object : Callback<DataLeagueResponse> {

            override fun onResponse(call: Call<DataLeagueResponse>?, response: Response<DataLeagueResponse>?) {
                view.hideLoading()
                Log.d("debug_rizal", "response : " + response!!.code())
                if (response.isSuccessful) {
                    if (response.body().events != null)
                        view.showTeamList(response.body().events!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataLeagueResponse>?, t: Throwable?) {
                view.hideLoading()
                Log.d("debug_rizal", "failed : " + t.toString())
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })
    }

    fun getNextMatchUnitTest(idEvent: String): DataLeagueResponse {
        responseLokal = DataLeagueResponse()
        view.showLoading()
        ServiceGenerator().getNextMatch(idEvent, object : Callback<DataLeagueResponse> {

            override fun onResponse(call: Call<DataLeagueResponse>?, response: Response<DataLeagueResponse>?) {
                view.hideLoading()
                Log.d("debug_rizal", "response : " + response!!.code())

                responseLokal = response.body()

                if (response.isSuccessful) {
                    if (response.body().events != null)
                        view.showTeamList(response.body().events!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataLeagueResponse>?, t: Throwable?) {
                view.hideLoading()
                Log.d("debug_rizal", "failed : " + t.toString())
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })

        return responseLokal
    }

    fun getLastMatchUnitTest(idEvent: String): DataLeagueResponse {
        responseLokal = DataLeagueResponse()
        view.showLoading()
        ServiceGenerator().getNextMatch(idEvent, object : Callback<DataLeagueResponse> {

            override fun onResponse(call: Call<DataLeagueResponse>?, response: Response<DataLeagueResponse>?) {
                view.hideLoading()
                Log.d("debug_rizal", "response : " + response!!.code())

                responseLokal = response.body()

                if (response.isSuccessful) {
                    if (response.body().events != null)
                        view.showTeamList(response.body().events!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataLeagueResponse>?, t: Throwable?) {
                view.hideLoading()
                Log.d("debug_rizal", "failed : " + t.toString())
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })

        return responseLokal
    }

    fun getDetailEvent(idEvent: String) {
        view.showLoading()
        ServiceGenerator().getDetailEvent(idEvent, object : Callback<DataLeagueResponse> {

            override fun onResponse(call: Call<DataLeagueResponse>?, response: Response<DataLeagueResponse>?) {
                view.hideLoading()
                Log.d("debug_rizal", "response : " + response!!.code())
                if (response.isSuccessful) {
                    if (response.body().events != null)
                        view.showTeamList(response.body().events!!)
                    else
                        view.showToast("Tidak ada data")
                } else {
                    view.showToast("Gagal mengambil data")
                }

            }

            override fun onFailure(call: Call<DataLeagueResponse>?, t: Throwable?) {
                view.hideLoading()
                Log.d("debug_rizal", "failed : " + t.toString())
                if (t != null) {
                    view.showToast(t.message!!)
                } else {
                    view.showToast("Gagal mengambil data")
                }
            }
        })
    }

}