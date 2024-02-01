package com.bintina.mynews

import android.os.Parcelable
import com.bintina.mynews.common.data.DataSource
import org.junit.Before
import org.junit.Test

class DataSourceUnitTest {

    private lateinit var dataSource: DataSource

    @Before
    fun setUp() {
        // Initialize your DataSource object here
        dataSource = DataSource
    }
/*

    @Test
    suspend fun testLoadNews() {
        // Create a mock object for DataSource
        val mockSource = mock(DataSource::class.java)

        val newsJsonPath = com.bintina.mynews.common.repository.mockResponse.json
        // Set up the behavior of the mock
        `when`(mockSource.loadNews()).thenReturn(FakeNewsRepository(newsJsonPath))

        // Call the function you want to test
        val result = mockSource.loadNews()

        // Assert the result
        assertEquals("Mocked News", result)
    }
*/

    // Assert the result
    //assertEquals(expectedValue, result) // Adjust as per your test case

}
/*
class MockSource() : DataSource, Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    fun loadNews():List<News?>?{
        //get mock json and convert to object. Return List<News?>
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MockSource> {
        override fun createFromParcel(parcel: Parcel): MockSource {
            return MockSource(parcel)
        }

        override fun newArray(size: Int): Array<MockSource?> {
            return arrayOfNulls(size)
        }
    }
}*/
