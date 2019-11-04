package com.murbano.aplicationrepos.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_repo.view.*

class RepoAdapterViewHolder(view:View) : RecyclerView.ViewHolder(view){
    val textName = view.text_name
    val textURL  = view.text_url
    val textDescription = view.text_description
}