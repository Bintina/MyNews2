package com.bintina.mynews.topstories.adapter

import android.text.TextUtils.substring
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ItemRowBinding
import com.bintina.mynews.model.News
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

class Adapter() : RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    var topStoriesList: List<News?> = mutableListOf<News?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int =topStoriesList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(topStoriesList[position])
    }
    class ItemViewHolder(private val view: ItemRowBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(news: News?) {
//Image View holder
            Glide.with(view.newsImage.context)
                .load(news?.multimedia?.first()?.url)
                .placeholder(R.drawable.ic_android_black_24dp)
                .centerCrop()
                .into(view.newsImage)

            //Location View holder
            view.location.text = if (news?.section?.isNotEmpty() == true && news?.subsection?.isNotEmpty() == true){

                "${news?.section} > ${news?.subsection}"
            } else {
                ""
            }.toString()

            //Date View holder
            val dateResponse = news?.publishedDate
            val dateFormatted = SimpleDateFormat("d/M/Y").format(dateResponse).toString()
            view.date.text = dateFormatted


            //Caption View holder

            view.caption.text = news?.abstract
        }
    }
}