package com.murbano.aplicationrepos.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.murbano.aplicationrepos.models.users.UserLogin
import com.murbano.aplicationrepos.models.users.UserLoginResponse
import com.murbano.aplicationrepos.repositories.UserInterfaceNet
import com.murbano.aplicationrepos.viewModels.Constants.CONECTION_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(val userInterfaceNet: UserInterfaceNet) : ViewModel() {

    var userLoginMutable: MutableLiveData<UserLoginResponse> = MutableLiveData()

    fun login(userLogin: UserLogin) {
        userInterfaceNet.loginUser(userLogin).enqueue(object : Callback<UserLoginResponse> {
            override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                val userResponse = UserLoginResponse(false, CONECTION_ERROR)
                userLoginMutable.value = userResponse
            }

            override fun onResponse(
                call: Call<UserLoginResponse>,
                response: Response<UserLoginResponse>
            ) {
                userLoginMutable.value = response.body()
            }
        })
    }


}