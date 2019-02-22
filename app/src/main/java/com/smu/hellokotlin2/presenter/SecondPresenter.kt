package com.smu.hellokotlin2.presenter

import android.content.Context
import com.smu.hellokotlin2.model.DataImageBadgeResponse
import com.smu.hellokotlin2.rest.ServiceGenerator
import com.smu.hellokotlin2.view.MainView
import com.smu.hellokotlin2.view.SecondView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by sapuser on 10/23/2018.
 */
class SecondPresenter(private val view: MainView, private val viewSecond: SecondView, private val context: Context) {

    fun getDetailTeam(idTeam: String, team: String) {
        view.showLoading()
        ServiceGenerator().getImageTeam(idTeam, object : Callback<DataImageBadgeResponse> {

            override fun onResponse(call: Call<DataImageBadgeResponse>?, response: Response<DataImageBadgeResponse>?) {
                view.hideLoading()
                if (response != null && response.isSuccessful) {
                    if (response.body().events != null)
                        viewSecond.showImage(response.body().events!!, team)
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
}