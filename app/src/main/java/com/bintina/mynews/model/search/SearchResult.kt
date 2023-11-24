package com.bintina.mynews.model.search


import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("response")
    val response: Response?,
    @SerializedName("status")
    val status: String?
)