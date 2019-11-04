package com.murbano.aplicationrepos.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murbano.aplicationrepos.R
import com.murbano.aplicationrepos.extensions.inflate
import com.murbano.aplicationrepos.models.repos.RepoResponse

class RepoAdapter : RecyclerView.Adapter<RepoAdapterViewHolder>() {

    var list : List<RepoResponse> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapterViewHolder = RepoAdapterViewHolder(parent.inflate(R.layout.adapter_repo))

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: RepoAdapterViewHolder, position: Int) {
        val repo = list[position]
        holder.textDescription.text = repo.description
        holder.textName.text = repo.name
        holder.textURL.text = repo.html_url
    }
}