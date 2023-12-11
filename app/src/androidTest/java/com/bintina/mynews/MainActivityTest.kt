package com.bintina.mynews

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityTest {

    @Test
    fun first_tab_api_call_is_topstories_world(){
        TODO("checks api call matches")
        //https://api.nytimes.com/svc/topstories/v2/world.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
    }

    @Test
    fun second_tab_api_call_is_popular_news(){
        TODO("checks api call matches")
        //https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
    }

    @Test
    fun third_tab_api_call_is_business_stories(){
        TODO("checks api call matches")
        //https://api.nytimes.com/svc/topstories/v2/business.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
    }

    @Test
    fun forth_tab_api_call_is_art_stories(){
        TODO("checks api call matches")
        //https://api.nytimes.com/svc/topstories/v2/arts.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
    }
    @Test
    fun fifth_tab_api_call_is_science_stories(){
        TODO("checks api call matches")
        //https://api.nytimes.com/svc/topstories/v2/science.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
    }

    @Test
    fun swiping_results_in_the_correct_screen(){
        TODO("check that swiping results in the correct API calls")
    }

    /*
    Possible further tests:
    1. Database methods return correct List<class>
    2. Null values in the results are filtered
     */
}
