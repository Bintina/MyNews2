package com.bintina.mynews.news.controller

/**
 * Interface definition for a callback to be invoked when a news item is clicked.
 */
interface OnNewsClickedListener {

    /**
     * Called when a news item is clicked to open the provided link.
     *
     * @param link The link associated with the clicked news item.
     */
    fun openLink(link: String)
}