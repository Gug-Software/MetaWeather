package com.jkgug.bold.metaweather.weather.city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.jkgug.bold.metaweather.databinding.FragmentCityBinding
import com.jkgug.bold.metaweather.model.SearchResult
import com.jkgug.bold.metaweather.model.WeatherBasicInfo
import com.jkgug.bold.metaweather.utils.hide
import com.jkgug.bold.metaweather.utils.show
import com.jkgug.bold.metaweather.weather.city.viewmodel.CityUiModel
import com.jkgug.bold.metaweather.weather.city.viewmodel.CityViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import org.koin.androidx.viewmodel.ext.android.viewModel

const val THREE_COLUMNS = 3

class CityFragment : Fragment() {

    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    private val cityViewModel: CityViewModel by viewModel()

    private val groupieAdapter = GroupAdapter<GroupieViewHolder>()
    private val navArgs by navArgs<CityFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(cityViewModel)
        showData(navArgs.searchResult)
        defineObservers()
        addListeners()
        configRecycler()
        cityViewModel.getData(navArgs.searchResult.woeid.toString())
    }

    private fun showData(searchResult: SearchResult) {
        binding.apply {
            textCity.text = searchResult.title
        }
    }

    private fun defineObservers() {
        cityViewModel.uiModel.observe(viewLifecycleOwner) {
            handleUiModel(it)
        }
    }

    private fun addListeners() {
        binding.apply {
            buttonBack.setOnClickListener { activity?.onBackPressed() }
        }
    }

    private fun configRecycler() {
        binding.recyclerItems.apply {
            adapter = groupieAdapter
            layoutManager = GridLayoutManager(context, THREE_COLUMNS)
        }
    }

    private fun handleUiModel(uiModel: CityUiModel) {
        when (uiModel) {
            CityUiModel.LoadingPpal -> loadingPpal()
            is CityUiModel.ShowDataCity -> showDataCity(uiModel.data)
            is CityUiModel.ShowDataCityForToday -> showDataForToday(uiModel.data)
        }
    }

    private fun loadingPpal() {
        binding.apply {
            show(viewLoading)
            hide(imageWeather, textWeatherName, recyclerItems, viewFuture, viewFutureToday)
        }
    }

    private fun showDataCity(data: Triple<WeatherBasicInfo, List<Item<*>>, List<Item<*>>>) {
        binding.apply {
            textWeatherName.text = data.first.weatherStateName
            Glide.with(root.context).load(data.first.weatherIconUrl).into(imageWeather)
            groupieAdapter.update(data.second)
            viewFuture.setItems(data.third)
            showViewsBasicData()
        }
    }

    private fun showDataForToday(data: List<Item<*>>) {
        binding.apply {
            show(viewFutureToday)
            viewFutureToday.setItems(data)
            viewFutureToday.hideText()
        }
    }

    private fun showViewsBasicData() = binding.apply {
        show(imageWeather, textWeatherName, recyclerItems, viewFuture)
        hide(viewLoading, viewFutureToday)
    }
}