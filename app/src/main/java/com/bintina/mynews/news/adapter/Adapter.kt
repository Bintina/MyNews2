package com.bintina.mynews.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ItemRowBinding
import com.bintina.mynews.model.news.News
import com.bintina.mynews.util.MyApp.Companion.clickedArticles

import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

class Adapter() : RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    var storiesList: List<News?> = emptyList<News?>()

    lateinit var listener: OnNewsClickedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding, parent.context)
    }


    override fun getItemCount(): Int = storiesList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(storiesList[position], listener)

    }

    class ItemViewHolder(private val view: ItemRowBinding, private val context: Context) : RecyclerView.ViewHolder(view.root) {
        fun bind(news: News?, listener: OnNewsClickedListener) {

            val newsLink = news?.url.toString()
            // Check if the article URL has been clicked
            val isClicked = clickedArticles.contains(newsLink)

            // Set background color or any other visual indicator
            if (isClicked) {
                view.cardContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.clicked_article_color))
            } else {
                // Reset background to default if not clicked
                view.cardContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            }

            //Image View

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

            //Section View
            view.section.text = "${news?.section} > ${news?.subsection}"

            //Date View

            view.date.text = SimpleDateFormat("d/M/Y", Locale.US).format(news?.publishedDate).toString()


            //Caption View

            view.caption.text = news?.abstract

            //News link linking
            view.cardContents.setOnClickListener { listener.openLink(newsLink) }

        }

        //Testing method

    }

}