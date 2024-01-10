package com.bintina.mynews.search.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ItemRowBinding
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.news.controller.OnNewsClickedListener
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * RecyclerView Adapter for displaying search results in a list.
 */
class Adapter() : RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    // List of search results
    var searchResultList = mutableListOf<Doc?>()

    // Listener for handling item click events
    lateinit var listener: OnNewsClickedListener

    /**
     * ViewHolder class representing individual items in the RecyclerView.
     */
    class ItemViewHolder(private val view: ItemRowBinding, private val context: Context) : RecyclerView.ViewHolder(view.root) {
        /**
         * Binds data to the ViewHolder.
         *
         * @param doc The search result item to bind.
         * @param listener The listener for item click events.
         */
        fun bind(doc: Doc?, listener: OnNewsClickedListener) {
            val newsLink = doc?.webUrl.toString()

            // Check if the article URL has been clicked
            val isClicked = MyApp.clickedArticles.contains(newsLink)

            // Set background color if clicked
            if (isClicked) {
                view.cardContainer.setBackgroundColor(ContextCompat.getColor(context, androidx.cardview.R.color.cardview_shadow_start_color))
            } else {
                // Reset background to default if not clicked
                view.cardContainer.setBackgroundColor(ContextCompat.getColor(context, androidx.cardview.R.color.cardview_shadow_end_color))
            }

            //Set Image View with Glide
            val newsImageUrl = "https://www.nytimes.com/${doc?.multimedia?.firstOrNull()?.url}"
            Glide.with(view.newsImage.context)
                .load(newsImageUrl)
                .placeholder(R.drawable.ic_android_black_24dp)
                .centerCrop()
                .into(view.newsImage)

            //Set Section View
            view.section.text = "${doc?.sectionName} > ${doc?.subsectionName}"

            //Set Date View
            val articleDate = doc?.pubDate
            val apiDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
            val parsedDate = apiDateFormat.parse(articleDate)
            view.date.text = SimpleDateFormat("d/M/Y", Locale.US).format(parsedDate).toString()

            //Set Caption View
            view.caption.text = doc?.abstract

            //Set click listener for News link
            view.cardContents.setOnClickListener{ listener.openLink(newsLink)}
        }
    }

    /**
     * Creates a new ViewHolder instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding, parent.context)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount(): Int = searchResultList.size

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(searchResultList[position], listener)
    }

}