package com.bintina.mynews.common.model.search


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("docs")
    val docs: List<Doc?>?,
    @SerializedName("meta")
    val meta: Meta?
)