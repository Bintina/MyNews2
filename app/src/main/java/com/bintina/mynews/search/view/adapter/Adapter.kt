package com.bintina.mynews.search.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ItemRowBinding
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.news.controller.OnNewsClickedListener
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

class Adapter() : RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    var searchResultList = mutableListOf<Doc?>()
    lateinit var listener: OnNewsClickedListener

    class ItemViewHolder(private val view: ItemRowBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(doc: Doc?, listener: OnNewsClickedListener) {
            //Image View
            val newsUrl = "https://www.nytimes.com/${doc?.multimedia?.firstOrNull()?.url}"
            Glide.with(view.newsImage.context)
                .load(newsUrl)
                .placeholder(R.drawable.ic_android_black_24dp)
                .centerCrop()
                .into(view.newsImage)

            //Section View
            view.section.text = "${doc?.sectionName} > ${doc?.subsectionName}"

            //Date View
            view.date.text = SimpleDateFormat("d/M/Y", Locale.US).format(doc?.pubDate).toString()

            //Caption View
            view.caption.text = doc?.abstract

            //News link linking
            val newsLink = doc?.webUrl.toString()
            view.cardContents.setOnClickListener{ listener.openLink(newsLink)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = searchResultList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(searchResultList[position], listener)
    }

}