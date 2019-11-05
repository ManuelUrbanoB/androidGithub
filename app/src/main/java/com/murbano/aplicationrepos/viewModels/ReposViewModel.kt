package com.murbano.aplicationrepos.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.murbano.aplicationrepos.models.repos.RepoResponse
import com.murbano.aplicationrepos.models.repos.RepoSearch
import com.murbano.aplicationrepos.models.repos.RepoUser
import com.murbano.aplicationrepos.models.repos.SearchRepoResponse
import com.murbano.aplicationrepos.repositories.RepoInterfaceNet
import com.murbano.aplicationrepos.utils.toast
import com.murbano.aplicationrepos.viewModels.Constants.CONECTION_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposViewModel (val reposInterfaceNet: RepoInterfaceNet) : ViewModel() {
    val reposUserMutable: MutableLiveData<List<RepoResponse>> = MutableLiveData()
    val reposSearchMutable: MutableLiveData<SearchRepoResponse> = MutableLiveData()

    fun getReposUser(repoUser: RepoUser, context:Context){
        reposInterfaceNet.getResposUser(repoUser.name, repoUser.page,repoUser.perPage).enqueue(object:
            Callback<List<RepoResponse>> {
            override fun onFailure(call: Call<List<RepoResponse>>, t: Throwable) {
                context.toast(CONECTION_ERROR)
            }

            override fun onResponse(
                call: Call<List<RepoResponse>>,
                response: Response<List<RepoResponse>>
            ) {
                reposUserMutable.value = response.body()
            }
        })
    }

    fun getReposSearch(repoSearch: RepoSearch, context:Context){
        reposInterfaceNet.getResposSearch(repoSearch.name, repoSearch.language, repoSearch.page,repoSearch.perPage).enqueue(object:
            Callback<SearchRepoResponse> {
            override fun onFailure(call: Call<SearchRepoResponse>, t: Throwable) {
                context.toast(CONECTION_ERROR)
            }

            override fun onResponse(
                call: Call<SearchRepoResponse>,
                response: Response<SearchRepoResponse>
            ) {
                reposSearchMutable.value = response.body()
            }
        })
    }
}