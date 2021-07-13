package com.adityaupadhye.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adityaupadhye.newsapp.R
import com.adityaupadhye.newsapp.databinding.ItemArticlePreviewBinding
import com.adityaupadhye.newsapp.models.Article
import com.bumptech.glide.Glide

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemArticlePreviewBinding): RecyclerView.ViewHolder(binding.root)

    private val diffUtilCallback= object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ= AsyncListDiffer(this, diffUtilCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticlePreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article= differ.currentList[position]
//        holder.itemView.apply {
//            Glide.with(this)
//                .load(article.urlToImage)
//                .into()
//        }

        with(holder){
            Glide.with(this.itemView).load(article.urlToImage)
                .placeholder(R.drawable.ic_baseline_image_24).into(this.binding.ivArticleImage)

            this.binding.tvSource.text= article.source?.name
            this.binding.tvTitle.text= article.title
            this.binding.tvDescription.text= article.description
            this.binding.tvPublishedAt.text= article.publishedAt

            this.itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? =null

    fun setOnItemClickListener(listener: (Article)->Unit){
        onItemClickListener = listener
    }
}