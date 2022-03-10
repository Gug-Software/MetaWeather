package com.jkgug.bold.metaweather.weather.search.viewmodel

import com.xwray.groupie.Item

sealed class SearchUiModel {

    object Loading : SearchUiModel()
    object Error : SearchUiModel()
    object NoQuery : SearchUiModel()
    object EmptyResults : SearchUiModel()
    object NotConnection : SearchUiModel()

    class ShowResults(
        val items: List<Item<*>>
    ) : SearchUiModel()

}