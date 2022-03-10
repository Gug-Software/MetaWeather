package com.jkgug.bold.metaweather.weather.city.views.regular

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkgug.bold.metaweather.R
import com.jkgug.bold.metaweather.databinding.WeatherFutureViewBinding
import com.jkgug.bold.metaweather.utils.hide
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class WeatherFutureView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: WeatherFutureViewBinding =
        WeatherFutureViewBinding.inflate(LayoutInflater.from(context), this, true)

    private val groupieAdapter = GroupAdapter<GroupieViewHolder>()

    init {
        configRecycler()
    }

    private fun configRecycler() {
        binding.apply {
            binding.recyclerItems.apply {
                adapter = groupieAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    fun setItems(listItems: List<Item<*>>) {
        groupieAdapter.update(listItems)
        binding.textTitle.text = binding.root.context.getString(
            R.string.city_text_future_days,
            listItems.size.toString()
        )
    }

    fun hideText() {
        hide(binding.textTitle)
    }

}