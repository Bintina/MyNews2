package com.bintina.mynews.search

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.bintina.mynews.R
import com.bintina.mynews.common.data.repository.DataSource
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
import com.bintina.mynews.common.util.MyApp.Companion.searchResultList
import com.bintina.mynews.common.util.getDefaultSearchStartDate
import com.bintina.mynews.common.util.getSelectedFilters
import com.bintina.mynews.common.util.instantiateTodaysDate
import com.bintina.mynews.search.controller.SearchActivity
import com.bintina.mynews.search.controller.SearchFragment
import com.bintina.mynews.search.controller.SearchResultsFragment
import com.bintina.mynews.search.view.adapter.Adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class SearchViewModel : ViewModel() {
    // Your ViewModel can have properties to store UI-related data
    lateinit var adapter: Adapter
    private val _searchResultList = MutableLiveData<List<Doc>>()
    val searchResultList: LiveData<List<Doc>> get() = _searchResultList

    private val _startDate = MutableLiveData<Date>()
    val startDate: LiveData<Date> get() = _startDate

    private val _endDate = MutableLiveData<Date>()
    val endDate: LiveData<Date> get() = _endDate

    val _keyword = MutableLiveData<String>()
    val keyword: LiveData<String> get() = _keyword

    private val _filters = MutableLiveData<String?>()
    val filters: LiveData<String?> get() = _filters

    private val _newResultList = MutableLiveData<List<Doc?>>()
    val newResultList: LiveData<List<Doc?>> get() = _newResultList



    /*lateinit var startDate: java.util.Date
    lateinit var endDate: java.util.Date
*/

    // Other properties needed for searching...

    init {
        // Initialize default values or perform other setup tasks
        instantiateTodaysDate()
        getDefaultSearchStartDate(currentDate)
    }



    fun setSearchDates(startDate: Date, endDate: Date) {
        _startDate.value = startDate
        _endDate.value = endDate
    }

    fun setFilters(
        arts: Boolean,
        business: Boolean,
        entreprenuers: Boolean,
        politics: Boolean,
        sports: Boolean,
        travel: Boolean
    ): String? {
        val filtersString =
            getSelectedFilters(arts, business, entreprenuers, politics, sports, travel)
        _filters.value = filtersString

        return filtersString
    }

    fun search(){
        var resultList = emptyList<Doc?>()
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("SearchVMLog", "keyword is $keyword, filters are $filters")

            val keywordString = keyword.toString()
            val filterString = filters.toString()
            try {
                resultList = DataSource.loadSearchResults(
                    keywordString,
                    startDate.value!!,
                    endDate.value!!,
                    filterString
                )

            } catch (e: Exception) {
                Log.d("SearchTryCatch", "Error is $e")
                emptyList<Doc?>()
            }
        }

        _newResultList.value = resultList

    }


    fun extractSearchEntries(
        keyword: String?,
        startDate: Date,
        endDate: Date,
        artsBoolean: Boolean,
        businessBoolean: Boolean
    ) {

    }

    fun goToSearch(){


    }
}
