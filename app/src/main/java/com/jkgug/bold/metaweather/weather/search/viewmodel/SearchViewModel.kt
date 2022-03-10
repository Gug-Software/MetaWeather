package com.jkgug.bold.metaweather.weather.search.viewmodel

import androidx.lifecycle.*
import com.jkgug.bold.metaweather.base.BaseViewModelCoroutines
import com.jkgug.bold.metaweather.weather.search.repository.ISearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: ISearchRepository
) : BaseViewModelCoroutines(), LifecycleObserver {

    private val _uiModel = MutableLiveData<SearchUiModel>()
    val uiModel: LiveData<SearchUiModel>
        get() = _uiModel

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        uiScope.launch {
            _uiModel.value = SearchUiModel.NoQuery
        }
    }

    fun trySearchByText(text: String) {
        if (text.isEmpty()) {
            _uiModel.value = SearchUiModel.NoQuery
        } else {
            _uiModel.value = SearchUiModel.Loading
            uiScope.launch {
                val results = searchRepository.searchByQuery(text)
                if (results.isEmpty()) {
                    _uiModel.value = SearchUiModel.EmptyResults
                } else {
                    _uiModel.value = SearchUiModel.ShowResults(results)
                }
            }
        }
    }

}