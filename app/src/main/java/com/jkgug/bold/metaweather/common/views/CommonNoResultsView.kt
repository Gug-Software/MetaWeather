package com.jkgug.bold.metaweather.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jkgug.bold.metaweather.R
import com.jkgug.bold.metaweather.databinding.CommonDataZeroViewBinding
import com.jkgug.bold.metaweather.databinding.CommonLoadingViewBinding
import com.jkgug.bold.metaweather.databinding.CommonNoResultsViewBinding
import com.jkgug.bold.metaweather.databinding.SearchResultItemViewBinding
import com.jkgug.bold.metaweather.model.SearchResult
import com.xwray.groupie.viewbinding.BindableItem

class CommonNoResultsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: CommonNoResultsViewBinding =
        CommonNoResultsViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding
    }

}