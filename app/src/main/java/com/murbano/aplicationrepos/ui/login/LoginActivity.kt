package com.murbano.aplicationrepos.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.murbano.aplicationrepos.R
import com.murbano.aplicationrepos.models.users.UserLogin
import com.murbano.aplicationrepos.models.users.UserLoginResponse
import com.murbano.aplicationrepos.ui.main.MainActivity
import com.murbano.aplicationrepos.viewModels.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModel()

    private val loginObserver: Observer<UserLoginResponse> = Observer { response ->
        responseLoginUser(response)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userViewModel.userLoginMutable.observe(this,loginObserver)
    }

    override fun onResume() {
        super.onResume()
        btn_login_user.setOnClickListener {
            loginUser()
        }
    }

    private fun  loginUser(){
        val username = text_username.editText?.text.toString()
        val password = text_password.editText?.text.toString()
        val userLogin = UserLogin(username,password)
        userViewModel.login(userLogin)
    }

    private fun responseLoginUser(userLoginResponse: UserLoginResponse){
        if(userLoginResponse.success){
            startActivity<MainActivity>("username" to text_username.editText?.text.toString())
            return
        }
        userLoginResponse.message?.let {  this.toast(it) }
    }
}
