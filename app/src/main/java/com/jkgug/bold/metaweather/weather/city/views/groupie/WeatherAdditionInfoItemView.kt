package com.jkgug.bold.metaweather.weather.city.views.groupie

import android.view.View
import com.jkgug.bold.metaweather.R
import com.jkgug.bold.metaweather.databinding.WeatherAdditionalInfoItemViewBinding
import com.jkgug.bold.metaweather.model.WeatherAdditionalInfo
import com.xwray.groupie.viewbinding.BindableItem

class WeatherAdditionInfoItemView(
    private val info: WeatherAdditionalInfo
) : BindableItem<WeatherAdditionalInfoItemViewBinding>() {

    override fun bind(binding: WeatherAdditionalInfoItemViewBinding, p1: Int) {
        binding.apply {
            textType.text = info.title
            textValue.text = info.value
        }
    }

    override fun getLayout(): Int = R.layout.weather_additional_info_item_view

    override fun initializeViewBinding(view: View): WeatherAdditionalInfoItemViewBinding {
        return WeatherAdditionalInfoItemViewBinding.bind(view)
    }

}