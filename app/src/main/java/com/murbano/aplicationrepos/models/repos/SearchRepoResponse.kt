package com.murbano.aplicationrepos.models.repos

data class SearchRepoResponse(val total_count:Int, val incomplete_results:Boolean, val items: List<RepoResponse>)