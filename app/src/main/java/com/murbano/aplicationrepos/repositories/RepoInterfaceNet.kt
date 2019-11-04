package com.murbano.aplicationrepos.repositories

import com.murbano.aplicationrepos.models.repos.RepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoInterfaceNet {

    @GET("repositories/myrepos/:name/:page:/perPage")
    fun getResposUser(@Path("name") username: String, @Path("page") page: Int, @Path("perPage") perPage: Int): Call<List<RepoResponse>>
}