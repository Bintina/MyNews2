package com.bintina.mynews.notifications.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ItemRowBinding
import com.bintina.mynews.model.search.Doc
import com.bintina.mynews.news.adapter.OnNewsClickedListener
import com.bumptech.glide.Glide

class Adapter(): RecyclerView.Adapter<com.bintina.mynews.notifications.adapter.Adapter.ItemViewHolder>() {
    var notificationsResultList = mutableListOf<Doc?>()
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
            /*val formatOutputDate = SimpleDateFormat("d/M/Y", Locale.US)
            val formatInputDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SSSX", Locale.US)

            val inputDate = formatInputDate.parse(inputText)
            val outputDate = formatOutputDate.format(inputDate)*/
            /*  val stringDate =doc?.pubDate.toString()

  val date = SimpleDateFormat("d/M/Y").format(stringDate).toString()
              Log.d("DateFormatAdapter", "The SimpleDateFormat output is $date")
  */         //  2023-11-24T10:02:20+0000
            //   view.date.text = DateTimeFormatter("d/M/Y").format(doc?.pubDate).toString()
            //DateFormat outputFormat = new SimpleDateFormat("MM/yyyy", Locale.US);
            //DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
            //
            //String inputText = "2012-11-17T00:00:00.000-05:00";
            //Date date = inputFormat.parse(inputText);
            //String outputText = outputFormat.format(date);

            //Caption View
            view.caption.text = doc?.abstract

            //News link linking
            val newsLink = doc?.webUrl.toString()
            view.cardContents.setOnClickListener{ listener.openLink(newsLink)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
Log.d("NotificationsAdapterLog","Notification Adapter onCreateView called")
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = notificationsResultList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(notificationsResultList[position], listener)
    }

}