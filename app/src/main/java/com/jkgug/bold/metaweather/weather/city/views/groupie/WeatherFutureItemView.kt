package com.jkgug.bold.metaweather.weather.city.views.groupie

import android.view.View
import com.bumptech.glide.Glide
import com.jkgug.bold.metaweather.R
import com.jkgug.bold.metaweather.databinding.WeatherFutureItemViewBinding
import com.jkgug.bold.metaweather.model.WeatherFutureInfo
import com.xwray.groupie.viewbinding.BindableItem

class WeatherFutureItemView(
    private val info: WeatherFutureInfo
) : BindableItem<WeatherFutureItemViewBinding>() {

    override fun bind(binding: WeatherFutureItemViewBinding, p1: Int) {
        binding.apply {
            textTitle.text = info.title
            textValue.text = info.value
            Glide.with(root.context).load(info.urlIcon).into(imageWeather)
        }
    }

    override fun getLayout(): Int = R.layout.weather_future_item_view

    override fun initializeViewBinding(view: View): WeatherFutureItemViewBinding {
        return WeatherFutureItemViewBinding.bind(view)
    }

}