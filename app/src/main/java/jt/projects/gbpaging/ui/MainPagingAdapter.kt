package jt.projects.gbpaging.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import jt.projects.gbpaging.databinding.ItemNewsBinding
import jt.projects.gbpaging.model.News

class MainPagingAdapter : PagingDataAdapter<News, MainPagingAdapter.Holder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = getItem(position) ?: return
        with(holder.binding) {
            tvTitle.text = data.title
            tvAwards.text = data.stars.toString()
            tvComments.text = data.comments.toString()
        }
    }

    class Holder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)
}

class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }

}