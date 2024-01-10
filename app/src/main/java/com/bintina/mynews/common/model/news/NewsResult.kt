package com.bintina.mynews.common.model.news


import com.google.gson.annotations.SerializedName

/**
 * Data class representing the result of a news API request.
 *
 * @param copyright Copyright information.
 * @param lastUpdated Timestamp indicating the last update.
 * @param numResults Number of results returned.
 * @param results List of [News] objects representing individual news articles.
 * @param section Section information.
 * @param status Status of the API request.
 * @param response List of [News] objects representing the response.
 */
data class NewsResult(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("results")
    val results: List<News?>,
    @SerializedName("section")
    val section: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("response")
    val response: List<News?>
)