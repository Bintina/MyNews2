package com.bintina.mynews.model.news





import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date
@Parcelize
data class News(
    @SerializedName("abstract")
    val `abstract`: String?,
    @SerializedName("byline")
    val byline: String?,
    @SerializedName("created_date")
    val createdDate: String?,
    @SerializedName("des_facet")
    val desFacet: List<String?>,
    @SerializedName("geo_facet")
    val geoFacet: List<String?>,
    @SerializedName("item_type")
    val itemType: String?,
    @SerializedName("kicker")
    val kicker: String?,
    @SerializedName("material_type_facet")
    val materialTypeFacet: String?,
    @SerializedName("org_facet")
    val orgFacet: List<String?>,
    @SerializedName("per_facet")
    val perFacet: List<String?>,
    @SerializedName("published_date")
    val publishedDate: Date?,
    @SerializedName("section")
    val section: String?,
    @SerializedName("short_url")
    val shortUrl: String?,
    @SerializedName("subsection")
    val subsection: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("updated_date")
    val updatedDate: String?,
    @SerializedName("uri")
    val uri: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("media")
        val media: List<Media>?,
    @SerializedName("multimedia")
        val multimedia: List<Multimedia>?
): Parcelable{


}
