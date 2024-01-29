package com.bintina.mynews

import com.bintina.mynews.common.data.DataSource
import org.mockito.Mock

class NewsFragmentUnitTest {

    @Mock
    lateinit var mockDataSource: DataSource
//convert response to object

    class mockNewsDataSource(
//overide loadNews to manually convert mock data
    ){
    }
//AS IS, before refactoring.
    //mock successful list
  //......without filters:
    //mock empty list result(no response) should return null
    //mock mixed list

    //DataSource test filters
}