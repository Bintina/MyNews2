package com.bintina.mynews.topstories.business.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ItemRowBinding
import com.bintina.mynews.model.News
import com.bintina.mynews.topstories.adapter.Adapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class Adapter(): RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    var businessNewsList: List<News?> = mutableListOf<News?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Adapter.ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = businessNewsList.size

    override fun onBindViewHolder(holder: Adapter.ItemViewHolder, position: Int) {
        holder.bind(businessNewsList[position])
    }

    class ItemViewHolder(private var view: ItemRowBinding): RecyclerView.ViewHolder(view.root){
        fun bind(news: News?){
            //Image View holder
            Glide.with(view.newsImage.context)
                .load(news?.multimedia?.first()?.url)
                .placeholder(R.drawable.ic_android_black_24dp)
                .centerCrop()
                .into(view.newsImage)


            //Date View holder
            view.date.text = news?.publishedDate.toString()

            //Location View holder
            view.location.text = news?.subsection

            //Caption View holder
            view.caption.text = news?.abstract
        }
    }
}