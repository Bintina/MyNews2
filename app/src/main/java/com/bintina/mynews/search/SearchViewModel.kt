package com.bintina.mynews.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
import com.bintina.mynews.common.util.getDefaultSearchStartDate
import com.bintina.mynews.common.util.instantiateTodaysDate
import com.bintina.mynews.search.view.adapter.Adapter
import java.util.Date

class SearchViewModel: ViewModel() {
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
}