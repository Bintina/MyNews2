package com.bintina.mynews.common.util



object Constants {
//New York Times....................................................................................
    //API Key and Base Url
    const val API_KEY = "zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf"
    const val BASE_URL = "https://api.nytimes.com/svc/"

    //Popular Stories
    //End Url
    const val POP_END_URL = "mostpopular/v2/viewed/1.json?api-key=$API_KEY"

    // Top Stories
    const val TOP_END_URL = "topstories/v2/world.json?api-key=$API_KEY"

    // Business
    const val BUSINESS_END_URL = "topstories/v2/business.json?api-key=$API_KEY"

    //Arts
    const val ART_END_URL = "topstories/v2/arts.json?api-key=$API_KEY"

    //Science
    const val SCI_END_URL = "topstories/v2/science.json?api-key=$API_KEY"

    //Search API
    const val SEARCH_END_URL = "search/v2/articlesearch.json"

//Worker............................................................................................
    @JvmField val NOTIFICATION_CHANNEL_NAME: CharSequence =
        "WorkManager Notifications"
    const val NOTIFICATION_CHANNEL_DESCRIPTION =
        "Shows notifications whenever work starts"
    @JvmField val NOTIFICATION_TITLE: CharSequence = "You have news!"
    const val CHANNEL_ID = "NOTIFICATION_CHANNEL"
    const val NOTIFICATION_ID = 1

    const val DELAY_TIME_MILLIS: Long = 3000
}