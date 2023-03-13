package com.sisnovate.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sisnovate.newsapp.databinding.SingleNewsBinding
import com.sisnovate.newsapp.network.model.news.NewsModelItem
import com.sisnovate.newsapp.ui.model.SingleNewsModel

class NewsAdapter(private val newsList: MutableList<NewsModelItem>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val binding: SingleNewsBinding = SingleNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    fun updateNews(newNews: List<NewsModelItem>?) {
        val diffResult = DiffUtil.calculateDiff(NewsDiffUtils(newsList, newNews!!))
        newsList.clear()
        newsList.addAll(newNews)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: SingleNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(newsModelItem: NewsModelItem) {
            binding.newsModel = SingleNewsModel(newsModelItem.title, newsModelItem.body)
            binding.executePendingBindings()
        }
    }

}

class NewsDiffUtils(private var oldData: List<NewsModelItem>, private var newData: List<NewsModelItem>):
    DiffUtil.Callback() {
    override fun getOldListSize(): Int  = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].id == newData[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].id == newData[newItemPosition].id &&
                oldData[oldItemPosition].title == newData[newItemPosition].title

    }

}