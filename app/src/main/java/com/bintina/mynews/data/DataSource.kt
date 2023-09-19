package com.bintina.mynews.data

import android.util.Log
import com.bintina.mynews.model.News
import com.bintina.mynews.topstories.api.ApiService

object DataSource {

    suspend fun loadTopStories(): List<News?>? {
        val apiCall = ApiService.create()
        val response = try{
            apiCall.getTopStories()
        } catch (e: Exception){
            Log.e("TopDatasourceLog", "Error is ${e.message.toString()}")
            null
        }

        return if(response != null && response.results!!.isNotEmpty()){
            response.results
        } else {
            null
        }
    }

    suspend fun loadPopularNews(){
        TODO()
    }

    suspend fun loadBusinessNews(){
        TODO()
    }
}