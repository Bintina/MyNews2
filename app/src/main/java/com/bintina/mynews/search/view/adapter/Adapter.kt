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

class Adapter() : RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    var searchResultList = mutableListOf<Doc?>()
    lateinit var listener: OnNewsClickedListener

    class ItemViewHolder(private val view: ItemRowBinding, private val context: Context) : RecyclerView.ViewHolder(view.root) {
        fun bind(doc: Doc?, listener: OnNewsClickedListener) {
            val newsLink = doc?.webUrl.toString()

            val isClicked = MyApp.clickedArticles.contains(newsLink)

            // Set background color or any other visual indicator
            if (isClicked) {
                view.cardContainer.setBackgroundColor(ContextCompat.getColor(context, androidx.cardview.R.color.cardview_shadow_start_color))
            } else {
                // Reset background to default if not clicked
                view.cardContainer.setBackgroundColor(ContextCompat.getColor(context, androidx.cardview.R.color.cardview_shadow_end_color))
            }

            //Image View
            val newsImageUrl = "https://www.nytimes.com/${doc?.multimedia?.firstOrNull()?.url}"
            Glide.with(view.newsImage.context)
                .load(newsImageUrl)
                .placeholder(R.drawable.ic_android_black_24dp)
                .centerCrop()
                .into(view.newsImage)

            //Section View
            view.section.text = "${doc?.sectionName} > ${doc?.subsectionName}"

            //Date View
            val apiDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
            val parsedDate = apiDateFormat.parse(doc?.pubDate)
            view.date.text = SimpleDateFormat("d/M/Y", Locale.US).format(parsedDate).toString()

            //Caption View
            view.caption.text = doc?.abstract

            //News link linking
            view.cardContents.setOnClickListener{ listener.openLink(newsLink)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = searchResultList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(searchResultList[position], listener)
    }

}