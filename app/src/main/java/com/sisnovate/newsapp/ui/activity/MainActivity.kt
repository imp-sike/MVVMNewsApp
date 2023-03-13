package com.sisnovate.newsapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.sisnovate.newsapp.R
import com.sisnovate.newsapp.databinding.ActivityMainBinding
import com.sisnovate.newsapp.ui.adapter.NewsAdapter
import com.sisnovate.newsapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        supportActionBar?.title = "Taaza Khavar"
        supportActionBar?.subtitle = "Miley khabar sabsey pehla"


        val adapter = NewsAdapter(mutableListOf())
        binding.newsRv.adapter = adapter
        binding.newsRv.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            mainViewModel.newsList.observe(this@MainActivity) {
                Log.d("HEHE", it.toString())
                adapter.updateNews(it)
                binding.progressBar.visibility = View.GONE
            }
        }

        mainViewModel.loadNews()

    }

}