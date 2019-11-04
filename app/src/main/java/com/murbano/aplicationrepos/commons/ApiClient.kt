package com.murbano.aplicationrepos.commons

import com.google.gson.GsonBuilder
import com.murbano.aplicationrepos.repositories.RepoInterfaceNet
import com.murbano.aplicationrepos.repositories.UserInterfaceNet
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var BASE_URL = "http://192.168.1.7:3000"
    private var retrofit: Retrofit? = null
    private var gson = GsonBuilder()
        .setLenient()
        .create()
    private fun createInstance():Retrofit{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        }
        return retrofit!!
    }

    fun usersInterface(): UserInterfaceNet = createInstance().create(
        UserInterfaceNet::class.java)
    fun reposInterface(): RepoInterfaceNet = createInstance().create(
        RepoInterfaceNet::class.java)

}