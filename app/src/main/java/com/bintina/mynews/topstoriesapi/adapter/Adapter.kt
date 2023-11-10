package com.bintina.mynews.topstoriesapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ItemRowBinding
import com.bintina.mynews.model.News
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

class Adapter() : RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    var storiesList: List<News?> = mutableListOf<News?>()

    lateinit var listener: OnNewsClickedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }


    override fun getItemCount(): Int =storiesList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(storiesList[position], listener)
    }
    class ItemViewHolder(private val view: ItemRowBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(news: News?, listener: OnNewsClickedListener) {
            //Image View
            Glide.with(view.newsImage.context)
                .load(news?.multimedia?.first()?.url)
                .placeholder(R.drawable.ic_android_black_24dp)
                .centerCrop()
                .into(view.newsImage)

            //Section View
            view.section.text = "${news?.section} > ${news?.subsection}"

            //Date View

            view.date.text = SimpleDateFormat("d/M/Y").format(news?.publishedDate).toString()


            //Caption View

            view.caption.text = news?.abstract

            //News link linking
            val newsLink = news?.url.toString()
            view.cardContents.setOnClickListener { listener.openLink(newsLink) }


        }

    }

}