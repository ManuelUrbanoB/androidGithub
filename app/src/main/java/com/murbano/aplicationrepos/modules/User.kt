package com.murbano.aplicationrepos.modules

import com.murbano.aplicationrepos.commons.ApiClient
import com.murbano.aplicationrepos.viewModels.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleUser = module {
    single { ApiClient.reposInterface() }
    viewModel { UserViewModel(get()) }
}