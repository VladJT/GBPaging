package jt.projects.gbpaging.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jt.projects.gbpaging.model.News

class MainAdapter() : RecyclerView.Adapter<NewsViewHolder>() {

    private var data: List<News> = arrayListOf()

    fun setData(data: List<News>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(parent)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}