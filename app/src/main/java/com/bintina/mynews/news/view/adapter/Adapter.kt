package com.bintina.mynews.news.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bintina.mynews.R
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.util.MyApp.Companion.clickedArticles
import com.bintina.mynews.databinding.ItemRowBinding
import com.bintina.mynews.news.controller.OnNewsClickedListener
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Adapter for populating the RecyclerView with news items.
 */
class Adapter() : RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    // List of news items
    var storiesList: List<News?> = emptyList<News?>()

    // Listener for news item clicks
    lateinit var listener: OnNewsClickedListener

    /**
     * Called when RecyclerView needs a new [ItemViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, parent.context)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount(): Int = storiesList.size

    /**
     * Called to bind the view holder to a specific position in the data set.
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(storiesList[position], listener)

    }

    /**
     * View holder for news items.
     */
    class ItemViewHolder(private val view: ItemRowBinding, private val context: Context) :
        RecyclerView.ViewHolder(view.root) {
        /**
         * Binds the news data to the view holder.
         */
        fun bind(news: News?, listener: OnNewsClickedListener) {
            val newsLink = news?.url.toString()

            // Check if the article URL has been clicked
            val isClicked = clickedArticles.contains(newsLink)

            // Set background color of clicked articles
            if (isClicked) {
                view.cardContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        androidx.cardview.R.color.cardview_shadow_start_color
                    )
                )
            } else {
                // Reset background to default if not clicked
                view.cardContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        androidx.cardview.R.color.cardview_shadow_end_color
                    )
                )
            }

            //Load image using Glide
            val newsImageUrl = if (news?.multimedia?.first()?.url != null) {
                news.multimedia.first().url
            } else {
                news?.media?.firstOrNull()?.mediaMetadata?.firstOrNull()?.url
            }

            Glide.with(view.newsImage.context)
                .load(newsImageUrl)
                .placeholder(R.drawable.ic_android_black_24dp)
                .centerCrop()
                .into(view.newsImage)

            //Set Section View
            view.section.text = "${news?.section} > ${news?.subsection}"

            //Set Date View
            view.date.text =
                SimpleDateFormat("d/M/Y", Locale.US).format(news?.publishedDate).toString()


            //Set Caption View
            view.caption.text = news?.abstract

            //Set click listener for News link
            view.cardContents.setOnClickListener { listener.openLink(newsLink) }

        }
    }
}