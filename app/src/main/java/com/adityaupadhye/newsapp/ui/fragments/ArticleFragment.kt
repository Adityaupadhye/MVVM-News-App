package com.adityaupadhye.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.adityaupadhye.newsapp.R
import com.adityaupadhye.newsapp.databinding.FragmentArticleBinding
import com.adityaupadhye.newsapp.models.Article
import com.adityaupadhye.newsapp.ui.NewsActivity
import com.adityaupadhye.newsapp.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class ArticleFragment: Fragment(R.layout.fragment_article) {

    private var _binding: FragmentArticleBinding?=null
    private val binding get() = _binding!!

    private lateinit var viewModel: NewsViewModel
    private val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentArticleBinding.inflate(inflater, container, false)

        viewModel= (activity as NewsActivity).viewModel
        val article: Article = args.article
        binding.webView.apply {
            webViewClient= WebViewClient()
            loadUrl(article.url!!)
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            view?.let { view ->
                Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}