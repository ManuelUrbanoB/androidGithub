package com.murbano.aplicationrepos.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.murbano.aplicationrepos.R
import com.murbano.aplicationrepos.models.repos.RepoResponse
import com.murbano.aplicationrepos.models.repos.RepoSearch
import com.murbano.aplicationrepos.models.repos.SearchRepoResponse
import com.murbano.aplicationrepos.ui.adapters.RepoAdapter
import com.murbano.aplicationrepos.viewModels.ReposViewModel
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val reposViewModel: ReposViewModel by viewModel()
    private var numberOfPage = 1
    private val perPage = 8

    private val reposObserver: Observer<SearchRepoResponse> = Observer { response ->
        response?.let { uploadItemRepoAdapter(response) }

    }

    private var repoAdapter = RepoAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        reposViewModel.reposSearchMutable.observe(this, reposObserver)
        initRecycler()
        configureButtonsPaginate()
        configureTextChanged()
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

    private fun configureTextChanged() {
        text_name.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) return
                reloadRepoPaginate()
            }
        })

        text_language.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) return
                reloadRepoPaginate()
            }
        })
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
        val name = text_name.editText?.text
        val language = text_language.editText?.text
        if (name.isNullOrEmpty()) return
        if (language.isNullOrEmpty()) return
        reposViewModel.getReposSearch(RepoSearch(name.toString(),language.toString(),numberOfPage,perPage), this)
    }

    private fun uploadItemRepoAdapter(response: SearchRepoResponse) {
        configureButtonsPaginate()
        var textTotal =( numberOfPage*perPage).toString()+"/"+response.total_count.toString()
        if (response.items.size < perPage){
            textTotal = response.total_count.toString() + "/"+ response.total_count.toString()
        }
        text_total.text = textTotal
        if (response.items.isEmpty() || response.items.size < perPage) {
            btn_next.visibility = View.INVISIBLE
        } else {
            btn_next.visibility = View.VISIBLE
        }
        repoAdapter.list = response.items
    }
}
