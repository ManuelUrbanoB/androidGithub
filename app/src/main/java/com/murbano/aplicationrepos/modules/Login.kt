package com.murbano.aplicationrepos.modules

import com.murbano.aplicationrepos.commons.ApiClient
import com.murbano.aplicationrepos.viewModels.ReposViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleLogin = module {
    single { ApiClient.usersInterface() }
    viewModel { ReposViewModel(get()) }
}