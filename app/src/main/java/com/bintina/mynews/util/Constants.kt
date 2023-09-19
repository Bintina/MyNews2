package com.bintina.mynews.util

object Constants {
//New York Times

    //unattached key
    const val API_KEY = "api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf"

    // Top Stories
    // base Url
    const val TOP_BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"
    //End Url: /world.json?api-key=yourkey
    const val TOP_END_URL = "world.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf"
    //https://api.nytimes.com/svc/topstories/v2/world.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf

    //Popular Stories
    //base URL
    const val POP_BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"
    //End Url
    const val POP_END_URL = "viewed/1.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf"
    //https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf

    // Business
    //End Url: /world.json?api-key=yourkey
    const val BUSINESS_END_URL = "business.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf"
    //https://api.nytimes.com/svc/topstories/v2/business.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf

}