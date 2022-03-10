package com.jkgug.bold.metaweather.weather.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.jkgug.bold.metaweather.R
import com.jkgug.bold.metaweather.databinding.FragmentSearchBinding
import com.jkgug.bold.metaweather.utils.hide
import com.jkgug.bold.metaweather.utils.navigate
import com.jkgug.bold.metaweather.utils.show
import com.jkgug.bold.metaweather.weather.search.viewmodel.SearchUiModel
import com.jkgug.bold.metaweather.weather.search.viewmodel.SearchViewModel
import com.jkgug.bold.metaweather.weather.search.views.SearchResultItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import org.koin.androidx.viewmodel.ext.android.viewModel

const val TWO_COLUMNS = 2

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModel()

    private val groupieAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(searchViewModel)
        defineObservers()
        addListeners()
        configRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun defineObservers() {
        searchViewModel.uiModel.observe(viewLifecycleOwner) {
            handleUiModel(it)
        }
    }

    private fun addListeners() {
        binding.editUser.doOnTextChanged { text, _, _, _ ->
            searchViewModel.trySearchByText(text.toString())
        }
        groupieAdapter.setOnItemClickListener { item, _ ->
            if (item is SearchResultItemView) {
                navigate(SearchFragmentDirections.actionFirstFragmentToSecondFragment(item.result))
            }
        }
    }

    private fun configRecycler() {
        binding.recyclerItems.apply {
            adapter = groupieAdapter
            layoutManager = GridLayoutManager(context, TWO_COLUMNS)
        }
    }

    private fun handleUiModel(uiModel: SearchUiModel) {
        when (uiModel) {
            SearchUiModel.EmptyResults -> emptyResults()
            SearchUiModel.Error -> error()
            SearchUiModel.Loading -> loading()
            SearchUiModel.NoQuery -> noQuery()
            SearchUiModel.NotConnection -> noConnection()
            is SearchUiModel.ShowResults -> showResults(uiModel.items)
        }
    }

    private fun noConnection() = binding.apply {
        show(viewNoConnection)
        hide(viewDataZero, recyclerItems, viewLoading, viewNoResults, viewError)
    }

    private fun showResults(items: List<Item<*>>) = binding.apply {
        show(recyclerItems)
        hide(viewDataZero, viewNoResults, viewLoading, viewNoConnection, viewError)
        groupieAdapter.update(items)
    }

    private fun emptyResults() = binding.apply {
        show(viewNoResults)
        hide(viewDataZero, recyclerItems, viewLoading, viewNoConnection, viewError)
    }

    private fun noQuery() = binding.apply {
        show(viewDataZero)
        hide(viewLoading, viewNoResults, recyclerItems, viewNoConnection, viewError)
    }

    private fun error() = binding.apply {
        show(viewError)
        hide(viewDataZero, recyclerItems, viewNoResults, viewNoConnection, viewLoading)
    }

    private fun loading() = binding.apply {
        show(viewLoading)
        hide(viewDataZero, recyclerItems, viewNoResults, viewNoConnection, viewError)
    }

}