package com.bintina.mynews.common.model.search


import com.google.gson.annotations.SerializedName

data class Keyword(
    @SerializedName("major")
    val major: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("value")
    val value: String?
)