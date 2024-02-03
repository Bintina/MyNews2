package com.bintina.mynews.common.model.news





import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.Date

/**
 * Data class representing a news article.
 *
 * @param `abstract` Summary or abstract of the news article.
 * @param byline Author information.
 * @param createdDate Date when the news article was created.
 * @param desFacet List of descriptors related to the article.
 * @param geoFacet List of geographic facets related to the article.
 * @param itemType Type of the news article.
 * @param kicker Kicker information.
 * @param materialTypeFacet Material type facet of the article.
 * @param orgFacet List of organizational facets related to the article.
 * @param perFacet List of person facets related to the article.
 * @param publishedDate Date when the news article was published.
 * @param section Section to which the article belongs.
 * @param shortUrl Shortened URL of the article.
 * @param subsection Subsection to which the article belongs.
 * @param title Title of the news article.
 * @param updatedDate Date when the news article was last updated.
 * @param uri URI of the article.
 * @param url URL of the article.
 * @param media List of [Media] objects associated with the article.
 * @param multimedia List of [Multimedia] objects associated with the article.
 */
@Parcelize
data class News(
    @SerializedName("abstract")
    val abstract: String?,
    @SerializedName("byline")
    val byline: String?,
    @SerializedName("created_date")
    val createdDate: LocalDateTime?,
    @SerializedName("des_facet")
    val desFacet: List<String?>?,
    @SerializedName("geo_facet")
    val geoFacet: List<String?>?,
    @SerializedName("item_type")
    val itemType: String?,
    @SerializedName("kicker")
    val kicker: String?,
    @SerializedName("material_type_facet")
    val materialTypeFacet: String?,
    @SerializedName("org_facet")
    val orgFacet: List<String?>?,
    @SerializedName("per_facet")
    val perFacet: List<String?>?,
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
