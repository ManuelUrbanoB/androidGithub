package com.murbano.aplicationrepos.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.murbano.aplicationrepos.R
import com.murbano.aplicationrepos.models.repos.RepoResponse
import com.murbano.aplicationrepos.viewModels.ReposViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.murbano.aplicationrepos.models.repos.RepoUser
import com.murbano.aplicationrepos.ui.adapters.RepoAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val userViewModel: ReposViewModel by viewModel()
    private var numberOfPage = 1
    private val perPage = 8

    private val reposObserver: Observer<List<RepoResponse>> = Observer { response ->
        uploadItemRepoAdapter(response)
    }

    private var username: String? = null
    private var repoAdapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = intent?.getStringExtra("username")
        userViewModel.reposUserMutable.observe(this, reposObserver)
        initRecycler()
        configureButtonsPaginate()
        btn_next.setOnClickListener {
            numberOfPage += 1
            reloadRepoPaginate()
        }
        btn_back.setOnClickListener {
            if (numberOfPage > 1) {
                numberOfPage -= 1
                reloadRepoPaginate()
            }
        }
    }

    private fun initRecycler() {
        recycler_repos_main.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_repos_main.adapter = repoAdapter
    }

    private fun configureButtonsPaginate() {
        if (numberOfPage == 1) {
            btn_back.visibility = View.INVISIBLE
        } else {
            btn_back.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        reloadRepoPaginate()
    }

    private fun reloadRepoPaginate() {
        username?.let { name ->
            userViewModel.getReposUser(RepoUser(name, numberOfPage, perPage), this)
        }
    }

    private fun uploadItemRepoAdapter(list: List<RepoResponse>) {
        configureButtonsPaginate()
        if (list.isEmpty() || list.size < perPage) {
            btn_next.visibility = View.INVISIBLE
        } else {
            btn_next.visibility = View.VISIBLE
        }
        repoAdapter.list = list
    }
}
