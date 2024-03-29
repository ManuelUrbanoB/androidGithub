package com.murbano.aplicationrepos.repositories

import com.murbano.aplicationrepos.models.repos.RepoResponse
import com.murbano.aplicationrepos.models.repos.SearchRepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoInterfaceNet {

    @GET("/repositories/myrepos/{name}/{page}/{per_page}")
    fun getResposUser(@Path("name") name: String, @Path("page") page: Int, @Path("per_page") perPage: Int): Call<List<RepoResponse>>

    @GET("/repositories/search/{name}/{language}/{page}/{per_page}")
    fun getResposSearch(
        @Path("name") name: String, @Path("language") language: String, @Path("page") page: Int, @Path(
            "per_page"
        ) perPage: Int
    ): Call<SearchRepoResponse>
}