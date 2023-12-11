package com.bintina.mynews.util



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
}