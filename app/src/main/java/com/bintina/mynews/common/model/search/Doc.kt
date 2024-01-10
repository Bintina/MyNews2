package com.bintina.mynews.common.model.search


import com.google.gson.annotations.SerializedName

/**
 * Data class representing a document (Doc) in the search result.
 *
 * @param `abstract` The abstract or summary of the document.
 * @param byline Information about the author(s) of the document.
 * @param documentType Type of the document.
 * @param headline The headline of the document.
 * @param id The unique identifier of the document.
 * @param keywords List of keywords associated with the document.
 * @param leadParagraph The lead paragraph or the first paragraph of the document.
 * @param multimedia List of multimedia items associated with the document.
 * @param newsDesk The news desk or category of the document.
 * @param printPage The print page of the document.
 * @param printSection The print section of the document.
 * @param pubDate The publication date of the document.
 * @param sectionName The section name of the document.
 * @param snippet A short snippet or preview of the document.
 * @param source The source or origin of the document.
 * @param subsectionName The subsection name of the document.
 * @param typeOfMaterial The type of material of the document.
 * @param uri The URI (Uniform Resource Identifier) of the document.
 * @param webUrl The web URL of the document.
 * @param wordCount The word count of the document.
 */
data class Doc(
    @SerializedName("abstract")
    val `abstract`: String?,
    @SerializedName("byline")
    val byline: Byline?,
    @SerializedName("document_type")
    val documentType: String?,
    @SerializedName("headline")
    val headline: Headline?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("keywords")
    val keywords: List<Keyword?>,
    @SerializedName("lead_paragraph")
    val leadParagraph: String?,
    @SerializedName("multimedia")
    val multimedia: List<Multimedia?>,
    @SerializedName("news_desk")
    val newsDesk: String?,
    @SerializedName("print_page")
    val printPage: String?,
    @SerializedName("print_section")
    val printSection: String?,
    @SerializedName("pub_date")
    val pubDate: String?,
    @SerializedName("section_name")
    val sectionName: String?,
    @SerializedName("snippet")
    val snippet: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("subsection_name")
    val subsectionName: String?,
    @SerializedName("type_of_material")
    val typeOfMaterial: String?,
    @SerializedName("uri")
    val uri: String?,
    @SerializedName("web_url")
    val webUrl: String?,
    @SerializedName("word_count")
    val wordCount: Int?
)