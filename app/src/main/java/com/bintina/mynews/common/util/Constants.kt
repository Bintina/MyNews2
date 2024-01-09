package com.bintina.mynews.common.util



object Constants {
//New York Times

    //unattached key
    const val API_KEY = "zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf"
    const val BASE_URL = "https://api.nytimes.com/svc/"

    //Popular Stories...............................................................................
    //End Url
    const val POP_END_URL = "mostpopular/v2/viewed/1.json?api-key=$API_KEY"
    //https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf


    // Top Stories..................................................................................
    //End Url: /world.json?api-key=yourkey
    const val TOP_END_URL = "topstories/v2/world.json?api-key=$API_KEY"
    //https://api.nytimes.com/svc/topstories/v2/world.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf

    // Business
    //End Url: /world.json?api-key=yourkey
    const val BUSINESS_END_URL = "topstories/v2/business.json?api-key=$API_KEY"
    //https://api.nytimes.com/svc/topstories/v2/business.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf

    //Arts
    //End Url: /arts.json?api-key=yourkey
    const val ART_END_URL = "topstories/v2/arts.json?api-key=$API_KEY"
    //https://api.nytimes.com/svc/topstories/v2/arts.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf

    //Science
    //End Url: science.json?api-key=yourkey
    const val SCI_END_URL = "topstories/v2/science.json?api-key=$API_KEY"
    //https://api.nytimes.com/svc/topstories/v2/science.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf



    //Search API....................................................................................
    const val SEARCH_BASE_URL = "https://api.nytimes.com/svc/"
    const val SEARCH_END_URL = "search/v2/articlesearch.json"
    //+ $formattedQuery
    ///articlesearch.json?q={query}&fq={filter}
    //https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=yourkey
//https://api.nytimes.com/svc/search/v2/articlesearch.json?q=olympics&api-key=zisbuhdclxat0g1lpmz0ynbagfhpkgqf
//working link
//https://api.nytimes.com/svc/search/v2/articlesearch.json?q=children&api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
    //https://api.nytimes.com/svc/search/v2/articlesearch.json?q=children{%26}api-key{%3D}zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf

    //Worker
    @JvmField val NOTIFICATION_CHANNEL_NAME: CharSequence =
        "WorkManager Notifications"
    const val NOTIFICATION_CHANNEL_DESCRIPTION =
        "Shows notifications whenever work starts"
    @JvmField val NOTIFICATION_TITLE: CharSequence = "You have news!"
    const val CHANNEL_ID = "NOTIFICATION_CHANNEL"
    const val NOTIFICATION_ID = 1

    // The name of the image manipulation work
    const val NOTIFICATIONS_WORK_NAME = "notification_work"
    const val NOTIFICATION_KEY_KEYWORD = "KEY_KEYWORD"
    const val NOTIFICATION_KEY_ARTS = "KEY_POLITICS"
    const val NOTIFICATION_KEY_BUSINESS = "KEY_BUSINESS"
    const val NOTIFICATION_KEY_ENTREPRENUERS = "KEY_ENTREPRENUERS"
    const val NOTIFICATION_KEY_POLITICS = "KEY_POLITICS"
    const val NOTIFICATION_KEY_SPORTS = "KEY_SPORTS"
    const val NOTIFICATION_KEY_TRAVEL = "KEY_TRAVEL"

    // Other keys
    const val OUTPUT_PATH = "blur_filter_outputs"
 //   const val KEY_IMAGE_URI = "KEY_IMAGE_URI"
    const val TAG_OUTPUT = "OUTPUT"

    const val DELAY_TIME_MILLIS: Long = 3000
}