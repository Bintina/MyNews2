package com.bintina.mynews.data

import android.util.Log
import com.bintina.mynews.model.News
import com.bintina.mynews.topstories.api.ApiService

object DataSource {


    suspend fun loadTopStories(): List<News?>? {
        val apiCall = ApiService.create()
        val response = try {
            apiCall.getTopStories()
        } catch (e: Exception) {
            Log.e("TopDatasourceLog", "Error is ${e.message.toString()}")
            null
        }

        //Filter Results..................................................
            val results = response?.results
            var parameterToCheckForNull = "section"

            val filteredForSection = results?.filterNot { News ->
                when (parameterToCheckForNull) {
                    "section" -> News?.section.isNullOrBlank()
                    else -> false
                }
            }

            parameterToCheckForNull = "subsection"
            val filteredForSubsection = filteredForSection?.filterNot { News ->
                when (parameterToCheckForNull) {
                    "subsection" -> News?.subsection.isNullOrBlank()
                    else -> false
                }
            }

            parameterToCheckForNull = "abstract"
            val filteredForAll = filteredForSubsection?.filterNot { News ->
                when (parameterToCheckForNull) {
                    "abstract" -> News?.abstract.isNullOrBlank()
                    else -> false
                }
            }
            val filteredList: List<News?>? = filteredForAll


        return if (filteredList != null && filteredList!!.isNotEmpty()) {
            filteredList
        } else {
            null
        }
    }

    suspend fun loadPopularNews(): List<News?>? {
        val apiCall = com.bintina.mynews.popular.api.ApiService.create()

        val response = try {
            apiCall.getPopularNews()
        } catch (e: Exception) {
            Log.e("PopDataSourceLog", "Error is ${e.message.toString()}")
            null
        }

        //Filter Results..................................................
        val results = response?.results
        var parameterToCheckForNull = "section"

        val filteredForSection = results?.filterNot { News ->
            when (parameterToCheckForNull) {
                "section" -> News?.section.isNullOrBlank()
                else -> false
            }
        }

        parameterToCheckForNull = "subsection"
        val filteredForSubsection = filteredForSection?.filterNot { News ->
            when (parameterToCheckForNull) {
                "subsection" -> News?.subsection.isNullOrBlank()
                else -> false
            }
        }

        parameterToCheckForNull = "abstract"
        val filteredForAll = filteredForSubsection?.filterNot { News ->
            when (parameterToCheckForNull) {
                "abstract" -> News?.abstract.isNullOrBlank()
                else -> false
            }
        }
        val filteredList: List<News?>? = filteredForAll

        return if (response != null && response.results.isNotEmpty()) {
            filteredList
        } else {
            null
        }
    }

    suspend fun loadBusinessNews(): List<News?>? {
        val apiCall = com.bintina.mynews.business.api.ApiService.create()

        val response = try {
            apiCall.getBusinessNews()
        } catch (e: Exception) {
            Log.e("BusDataSourceLog", "Error is ${e.message.toString()}")
            null
        }

        //Filter Results..................................................
        val results = response?.results
        var parameterToCheckForNull = "section"

        val filteredForSection = results?.filterNot { News ->
            when (parameterToCheckForNull) {
                "section" -> News?.section.isNullOrBlank()
                else -> false
            }
        }

        parameterToCheckForNull = "subsection"
        val filteredForSubsection = filteredForSection?.filterNot { News ->
            when (parameterToCheckForNull) {
                "subsection" -> News?.subsection.isNullOrBlank()
                else -> false
            }
        }

        parameterToCheckForNull = "abstract"
        val filteredForAll = filteredForSubsection?.filterNot { News ->
            when (parameterToCheckForNull) {
                "abstract" -> News?.abstract.isNullOrBlank()
                else -> false
            }
        }
        val filteredList: List<News?>? = filteredForAll

        return if (response != null && response.results.isNotEmpty()) {
            filteredList
        } else {
            null
        }
    }
}