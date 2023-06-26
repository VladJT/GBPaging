package jt.projects.gbpaging.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jt.projects.gbpaging.databinding.ItemNewsBinding
import jt.projects.gbpaging.model.News


class NewsViewHolder private constructor(
    private val binding: ItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(data: News) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            with(binding) {
                tvTitle.text = data.title
                tvAwards.text = data.stars.toString()
                tvComments.text = data.comments.toString()
            }
        }
    }
}