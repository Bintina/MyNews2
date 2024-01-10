package com.bintina.mynews.common.model.search


import com.google.gson.annotations.SerializedName

/**
 * Data class representing the result of a search operation.
 *
 * @param copyright Copyright information related to the search result.
 * @param results Object containing the actual search results.
 * @param status Status of the search operation.
 */
data class SearchResult(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("response")
    val results: Result?,
    @SerializedName("status")
    val status: String?
)