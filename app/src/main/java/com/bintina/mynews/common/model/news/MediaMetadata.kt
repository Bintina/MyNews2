package com.bintina.mynews.common.model.news


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaMetadata(
    @SerializedName("format")
    val format: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
): Parcelable{

}