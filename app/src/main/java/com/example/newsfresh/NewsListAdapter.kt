package com.example.newsfresh

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter( private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_view,parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.title.text = currentItem.title
        holder.source.text = currentItem.source
        holder.date.text = currentItem.date
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)

    }

    override fun getItemCount(): Int {
        return items.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<News>)
    {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val title : TextView = itemView.findViewById(R.id.title)
    val image : ImageView = itemView.findViewById(R.id.image)
    val source : TextView = itemView.findViewById(R.id.source)
    val date : TextView = itemView.findViewById(R.id.date)
}
interface NewsItemClicked{
    fun onItemClicked(item: News)
}