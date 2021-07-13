package com.adityaupadhye.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.adityaupadhye.newsapp.R
import com.adityaupadhye.newsapp.databinding.ActivityNewsBinding
import com.adityaupadhye.newsapp.db.ArticleDatabase
import com.adityaupadhye.newsapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository= NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory= NewsViewModelProviderFactory(application, repository)
        //Log.e("news", "key= "+getString(R.string.news_api_key))
        viewModel= ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(
            navHostFragment.navController
            //findNavController(R.id.newsNavHostFragment)
        )
    }
}