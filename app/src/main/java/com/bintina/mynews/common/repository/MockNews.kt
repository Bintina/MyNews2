package com.bintina.mynews.common.repository

import com.bintina.mynews.common.model.news.Media
import com.bintina.mynews.common.model.news.MediaMetadata
import com.bintina.mynews.common.model.news.Multimedia
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.util.getApiDates
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

data class MockNews(override val size: Int) : List<News?> {
    public val mockNewsList: List<News?> by lazy {
        generateMockNews()
    }

    private fun generateMockNews(): List<News?> {
        val createdDate = stringDateToLocalDateTime("2024-01-19T05:25:11-05:00")
        val publishedDate = stringDateToDate("2024-01-19T05:25:11-05:00")
        val createdDate2 = stringDateToLocalDateTime("2024-01-19T00:01:26-05:00")
        val publishedDate2 = stringDateToDate("2024-01-19T00:01:26-05:00")

        // Create a list of mock News objects
        return listOf(
            News(
                "Protesters in Tel Aviv demanded urgent action to free hostages, after a war cabinet minister said Israel should seek a longer cease-fire with Hamas to allow the captives to be released.",
                "",
                createdDate,
                null,
                null,

                "Promo",
                "",
                "",
                null,
                null,
                publishedDate,
                "",
                "",
                "",
                "Widening Mideast Crisis: Frustration Grows in Israel Over Fate of Gaza Hostages",
                "2024-01-19T08:31:58-05:00",
                "nyt://promo/437da112-e7ac-5402-b332-f51df823175b",
                "https://www.nytimes.com/live/2024/01/19/world/israel-hamas-news",
                listOf(
                    Media(
                        123,
                        "",
                        "",
                        listOf(
                            MediaMetadata("", 0, "", 0),
                            MediaMetadata("", 0, "", 0)
                        ),
                        "",
                        ""
                    ),
                    Media(
                        123,
                        "",
                        "",
                        listOf(
                            MediaMetadata("", 0, "", 0),
                            MediaMetadata("", 0, "", 0)
                        ),
                        "",
                        ""
                    )
                ),
                listOf(
                    Multimedia(

                        "Alexandre Meneghini/Reuters",
                        "Alexandre Meneghini/Reuters",
                        "Super Jumbo",
                        1366,
                        "photo",
                        "image",
                        "https://static01.nyt.com/images/2024/01/19/multimedia/19israel-hamas-header-hostages0800ET-ltcf/19israel-hamas-header-hostages0800ET-ltcf-superJumbo.jpg",
                        2048
                    ),
                    Multimedia(
                        "Protesters in Tel Aviv on Friday.",
                        "Alexandre Meneghini/Reuters",
                        "Super Jumbo",
                        1366,
                        "photo",
                        "image",
                        "https://static01.nyt.com/images/2024/01/19/multimedia/19israel-hamas-header-hostages0800ET-ltcf/19israel-hamas-header-hostages0800ET-ltcf-superJumbo.jpg",
                        2048
                    ),
                    Multimedia(
                        "Protesters in Tel Aviv on Friday.",
                        "Alexandre Meneghini/Reuters",
                        "threeByTwoSmallAt2X",
                        400,
                        "photo",
                        "image",
                        "https://static01.nyt.com/images/2024/01/19/multimedia/19israel-hamas-header-hostages0800ET-ltcf/19israel-hamas-header-hostages0800ET-ltcf-threeByTwoSmallAt2X.jpg",
                        600
                    ),
                    Multimedia(
                        "Protesters in Tel Aviv on Friday.",
                        "Alexandre Meneghini/Reuters",
                        "Large Thumbnail",
                        150,
                        "photo",
                        "image",
                        "https://static01.nyt.com/images/2024/01/19/multimedia/19israel-hamas-header-hostages0800ET-ltcf/19israel-hamas-header-hostages0800ET-ltcf-thumbLarge.jpg",
                        150
                    )
                )
            ),
            News(
                "With a decisive turn toward youth and conservative ideas, the French president sets a path for his reshuffled government.",
                "By Roger Cohen",
                createdDate2,
                null,
                null,
                "Article",
                "news Analysis",
                "",
                null,
                null,
                publishedDate2,
                "world",
                "",
                "europe",
                "Macron Shifts Rightward, and Charts a New Course",
                "2024-01-19T04:14:03-05:00",
                "nyt://article/be0b5a0d-6cf7-5851-b51f-cf8c073a2b98",
                "https://www.nytimes.com/2024/01/19/world/europe/france-macron-speech.html",
                listOf(
                    Media(
                        123,
                        "",
                        "",
                        listOf(
                            MediaMetadata("", 0, "", 0),
                            MediaMetadata("", 0, "", 0)
                        ),
                        "",
                        ""
                    ),
                    Media(
                        123,
                        "",
                        "",
                        listOf(
                            MediaMetadata("", 0, "", 0),
                            MediaMetadata("", 0, "", 0)
                        ),
                        "",
                        ""
                    )
                ),
                listOf(
                    Multimedia(
                        "President Emmanuel Macron of France on Tuesday during a news conference at the Elysee Palace.",
                        "Ludovic Marin/Agence France-Presse — Getty Images",
                        "Super Jumbo",
                        1365,
                        "photo",
                        "image",
                        "https://static01.nyt.com/images/2024/01/18/multimedia/18france-1-lhtj/18france-1-lhtj-superJumbo.jpg",
                        2048
                    ),
                    Multimedia(
                        "President Emmanuel Macron of France on Tuesday during a news conference at the Elysee Palace.",
                        "Ludovic Marin/Agence France-Presse — Getty Images",
                        "threeByTwoSmallAt2X",
                        400,
                        "photo",
                        "image",
                        "https://static01.nyt.com/images/2024/01/18/multimedia/18france-1-lhtj/18france-1-lhtj-threeByTwoSmallAt2X.jpg",
                        600
                    ),
                    Multimedia(
                        "President Emmanuel Macron of France on Tuesday during a news conference at the Elysee Palace.",
                        "Ludovic Marin/Agence France-Presse — Getty Images",
                        "Large Thumbnail",
                        150,
                        "photo",
                        "image",
                        "https://static01.nyt.com/images/2024/01/18/multimedia/18france-1-lhtj/18france-1-lhtj-thumbLarge.jpg",
                        150
                    )
                )

            )
        )
    }

    override fun contains(element: News?): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<News?>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): News? {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<News?> {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<News?> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<News?> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<News?> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: News?): Int {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: News?): Int {
        TODO("Not yet implemented")
    }

    fun stringDateToLocalDateTime(stringDate: String): LocalDateTime {
        // Define the date time formatter for the input string
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")

        // Parse the string into a LocalDateTime object
        val localDateTime = LocalDateTime.parse(stringDate, formatter)


        return localDateTime
    }

    fun stringDateToDate(stringDate: String): Date {

        val localDateTimeFormat = stringDateToLocalDateTime(stringDate)

        // Convert LocalDateTime to Date
        val date = Date.from(localDateTimeFormat.atZone(ZonedDateTime.now().zone).toInstant())

        // Print the result
        println(date)

        return date
    }
}
