package com.murbano.aplicationrepos.repositories

import com.murbano.aplicationrepos.models.users.UserLogin
import com.murbano.aplicationrepos.models.users.UserLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserInterfaceNet {

    @POST("/users/login")
    fun loginUser(@Body user: UserLogin): Call<UserLoginResponse>


}