package com.bintina.mynews.repository

import com.bintina.mynews.common.model.news.Media
import com.bintina.mynews.common.model.news.MediaMetadata
import com.bintina.mynews.common.model.news.Multimedia
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.model.search.Byline
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.common.model.search.Headline
import com.bintina.mynews.common.model.search.Keyword
import com.bintina.mynews.common.model.search.Legacy
import com.bintina.mynews.common.model.search.Person
import com.bintina.mynews.common.util.getApiDates
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

data class MockNews(override val size: Int) : List<News?> {

    val mockNewsList: List<News?> by lazy {
        generateMockNews()
    }

    private fun generateMockNews(): List<News?> {
        val publishedDate = stringDateToDate("2024-01-19T05:25:11-05:00")
        val publishedDate2 = stringDateToDate("2024-01-19T00:01:26-05:00")

        // Create a list of mock News objects
        return listOf(
            News(
                "Protesters in Tel Aviv demanded urgent action to free hostages, after a war cabinet minister said Israel should seek a longer cease-fire with Hamas to allow the captives to be released.",
                "",
                null,
                null,
                "Promo",
                "",
                "",
                null,
                null,
                publishedDate = publishedDate,
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
                null,
                null,
                "Article",
                "news Analysis",
                "",
                null,
                null,
                publishedDate = publishedDate2,
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

    private fun generateMockSearchResults(): List<Doc?> {
        // Create a list of mock News objects
        return listOf(
            Doc(
                "The fulminations surrounding the world’s biggest pop icon — and girlfriend of Chiefs tight end Travis Kelce — reached the stratosphere after Kansas City made it to the Super Bowl.",
                Byline(
                    "",
                    "",
                    listOf(Person("", "", "", "", null, 1, "", null))
                ),
                "",
                Headline(
                    null,
                    "",
                    "Taylor Swift, Travis Kelce and a MAGA Meltdown",
                    null,
                    "Far Right Sees  A Secret Agent,  Not a Pop Star",
                    null,
                    null
                ),
                "",
                listOf(
                    Keyword("N", "persons", 1, "Swift, Taylor"),
                    Keyword("N", "persons", 2, "Kelce, Travis"),
                    Keyword("N", "subject", 3, "Conspiracy Theories"),
                    Keyword("N", "subject", 4, "Right-Wing Extremism and Alt-Right"),
                    Keyword("N", "persons", 5, "Trump, Donald J"),
                    Keyword("N", "persons", 6, "Biden, Joseph R Jr"),
                    Keyword("N", "subject", 7, "Presidential Election of 2024")
                ),
                "For football fans eager to see a new team in the Super Bowl, the conference championship games on Sunday that sent the Kansas City Chiefs and San Francisco 49ers back to the main event of American sports culture were sorely disappointing.",
                listOf(
                    com.bintina.mynews.common.model.search.Multimedia(
                        "",
                        "",
                        "articleLarge",
                        400,
                        Legacy(
                            "",
                            0,
                            0,
                            "",
                            0,
                            0,
                            "images/2024/01/29/multimedia/29pol-taylor-topart-ckzf/29pol-taylor-topart-ckzf-articleLarge.jpg",
                            400,
                            600
                        ),
                        0,
                        "xlarge",
                        "xlarge",
                        "image",
                        "images/2024/01/29/multimedia/29pol-taylor-topart-ckzf/29pol-taylor-topart-ckzf-articleLarge.jpg",
                        600
                    ),
                    com.bintina.mynews.common.model.search.Multimedia(
                        "",
                        "",
                        "popup",
                        433,
                        Legacy("", 0, 0, "", 0, 0, "", 0, 0),
                        0,
                        "popup",
                        "popup",
                        "image",
                        "images/2024/01/29/multimedia/29pol-taylor-topart-ckzf/29pol-taylor-topart-ckzf-popup.jpg",
                        650
                    )

                ),
                "",
                "1",
                "A",
                "",
                "",
                "The fulminations surrounding the world’s biggest pop icon — and girlfriend of Chiefs tight end Travis Kelce — reached the stratosphere after Kansas City made it to the Super Bowl.",
                "The New York Times",
                "",
                "",
                "",
                "https://www.nytimes.com/2024/01/30/us/politics/taylor-swift-travis-kelce-trump.html",
                0
            ),
            Doc(
                "",
                Byline(
                    "", "By Jonathan Weisman",
                    listOf(
                        Person("Jonathan", "Weisman", "", "", null, 1, "reported", null)
                    )
                ),
                "article",
                Headline(
                    null,
                    "",
                    "",
                    null,
                    "",
                    null,
                    null
                ),
                "nyt://article/9adeb8e9-1bcc-52b4-a97d-7373db080ee7",
                listOf(
                    Keyword("N", "persons", 1, "abd"),
                    Keyword("N", "persons", 2, "bcd"),
                    Keyword("N", "subject", 3, "cde"),
                    Keyword("N", "subject", 4, "def"),
                    Keyword("N", "persons", 5, "efg"),
                    Keyword("N", "persons", 6, "fgh"),
                    Keyword("N", "subject", 7, "ghi")
                ),
                "",
                listOf(
                    com.bintina.mynews.common.model.search.Multimedia(
                        "",
                        "",
                        "",
                        0,
                        Legacy(
                            "",
                            0,
                            0,
                            "",
                            0,
                            0,
                            "",
                            0,
                            0
                        ),

                        0,
                        "",
                        "",
                        "",
                        "",
                        0
                    ),
                    com.bintina.mynews.common.model.search.Multimedia(
                        "",
                        "",
                        "",
                        0,
                        Legacy(
                            "",
                            0,
                            0,
                            "",
                            0,
                            0,
                            "",
                            0,
                            0
                        ),
                        0,
                        "",
                        "",
                        "",
                        "",
                        0
                    )

                ),
                "Politics",
                "",
                "",
                "2024-01-30T14:26:13+0000",
                "U.S.",
                "",
                "",
                "Politics",
                "News",
                "nyt://article/9adeb8e9-1bcc-52b4-a97d-7373db080ee7",
                "",
                1283
            ),
            Doc(
                "The state has offered defeated candidates second chances and improbable victories, but Nikki Haley couldn’t get voters to throw her a life vest.",
                Byline(
                    "", "",
                    listOf(
                        Person(
                            "",
                            "",
                            "",
                            "",
                            null,
                            1,
                            "",
                            null
                        )
                    )
                ),
                "",
                Headline(
                    null,
                    "News Analysis",
                    "How Haley Lost New Hampshire: Ignoring Lessons From Underdogs of the Past",
                    null,
                    "Haley Ignored Lessons From Underdogs of New Hampshire’s Past",
                    null,
                    null
                ),
                "",
                listOf(
                    Keyword("N", "subject", 1, "Presidential Election of 2024"),
                    Keyword("N", "subject", 2, "United States Politics and Government"),
                    Keyword("N", "subject", 3, "United States Politics and Government"),
                    Keyword("N", "organizations", 4, "Republican Party"),
                    Keyword("N", "persons", 5, "Haley, Nikki R"),
                    Keyword("N", "persons", 6, "Trump, Donald J"),
                    Keyword("N", "persons", 7, "McCain, John")
                ),
                "Senator John McCain’s first town hall in May 1999 was awful. Thirteen people milled around at a nearly empty American Legion Hall in Manchester, and only nine of them were still deciding whom to vote for in the first-in-the-nation primary.",
                listOf(
                    com.bintina.mynews.common.model.search.Multimedia(
                        "",
                        "",
                        "articleLarge",
                        600,
                        Legacy(
                            "",
                            0,
                            0,
                            "",
                            0,
                            0,
                            "images/2024/01/23/multimedia/HFOpol-howhaleylost-top-maybe-kplf/HFOpol-howhaleylost-top-maybe-kplf-articleLarge.jpg",
                            600,
                            600
                        ),

                        0,
                        "xlarge",
                        "xlarge",
                        "image",
                        "images/2024/01/23/multimedia/HFOpol-howhaleylost-top-maybe-kplf/HFOpol-howhaleylost-top-maybe-kplf-articleLarge.jpg",
                        600
                    ),
                    com.bintina.mynews.common.model.search.Multimedia(
                        "",
                        "",
                        "",
                        500,
                        Legacy(
                            "",
                            0,
                            0,
                            "",
                            0,
                            0,
                            "",
                            0,
                            0
                        ),
                        0,
                        "popup",
                        "popup",
                        "image",
                        "images/2024/01/23/multimedia/HFOpol-howhaleylost-top-maybe-kplf/HFOpol-howhaleylost-top-maybe-kplf-popup.jpg",
                        500
                    )

                ),
                "",
                "15",
                "A",
                "",
                "",
                "The state has offered defeated candidates second chances and improbable victories, but Nikki Haley couldn’t get voters to throw her a life vest.",
                "The New York Times",
                "",
                "",
                "",
                "https://www.nytimes.com/2024/01/24/us/politics/haley-new-hampshire-loss.html",
                1283
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
        val date =
            Date.from(localDateTimeFormat.atZone(ZonedDateTime.now().zone).toInstant())

        // Print the result
        println(date)

        return date
    }
}
