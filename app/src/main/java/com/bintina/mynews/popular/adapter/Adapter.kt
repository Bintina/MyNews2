package com.bintina.mynews.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ItemRowBinding
import com.bintina.mynews.model.News
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class Adapter(): RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    var popularNewsList: List<News?> = mutableListOf<News?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = popularNewsList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(popularNewsList[position])
    }

    class ItemViewHolder(private val view: ItemRowBinding): RecyclerView.ViewHolder(view.root){
        fun bind(news: News?){
            //Image View holder
            Glide.with(view.newsImage.context)
                .load(news?.media?.first()?.mediaMetadata?.first()?.url)
                .placeholder(R.drawable.ic_android_black_24dp)
                .centerCrop()
                .into(view.newsImage)

//            Picasso.with(view.newsImage.context)
//                .load(news?.media?.first()?.mediaMetadata?.first()?.url)
//                .placeholder(R.drawable.ic_android_black_24dp)
//                .into(view.newsImage)

            //Date View holder
            view.date.text = news?.publishedDate.toString()

            //Location View holder
            view.location.text = news?.subsection

            //Caption View holder
            view.caption.text = news?.abstract
        }
    }
}