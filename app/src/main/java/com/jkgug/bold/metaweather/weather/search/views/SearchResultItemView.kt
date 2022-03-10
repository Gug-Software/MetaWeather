package com.jkgug.bold.metaweather.weather.search.views

import android.view.View
import com.jkgug.bold.metaweather.R
import com.jkgug.bold.metaweather.databinding.SearchResultItemViewBinding
import com.jkgug.bold.metaweather.model.SearchResult
import com.xwray.groupie.viewbinding.BindableItem

class SearchResultItemView(
    val result: SearchResult
) : BindableItem<SearchResultItemViewBinding>() {

    override fun bind(binding: SearchResultItemViewBinding, p1: Int) {
        binding.apply {
            textCity.text = result.title
            textType.text = result.type
        }
    }

    override fun getLayout(): Int = R.layout.search_result_item_view

    override fun initializeViewBinding(view: View): SearchResultItemViewBinding {
        return SearchResultItemViewBinding.bind(view)
    }

}