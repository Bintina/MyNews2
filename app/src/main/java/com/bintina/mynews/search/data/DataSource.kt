package com.bintina.mynews.search.data

import android.content.Context
import com.bintina.mynews.util.MyApp.Companion.DATE_RANGE
import com.bintina.mynews.util.MyApp.Companion.FILTERS
import com.bintina.mynews.util.MyApp.Companion.QUERY_TERM
import com.bintina.mynews.util.MyApp.Companion.savedDateRange
import com.bintina.mynews.util.MyApp.Companion.savedFilters
import com.bintina.mynews.util.MyApp.Companion.savedQuery
import com.bintina.mynews.util.preferenceToString
import kotlin.coroutines.coroutineContext

object DataSource {

    //Fetching user entries
    fun fetchQueryDetails(context: Context){
        savedQuery = preferenceToString(context, QUERY_TERM)
        savedDateRange = preferenceToString(context, DATE_RANGE)
        savedFilters = preferenceToString(context, FILTERS)

    }

    //Calls
     fun loadSearchResults(context: Context){
        fetchQueryDetails(context = context)
        println("$savedQuery")
    }
}