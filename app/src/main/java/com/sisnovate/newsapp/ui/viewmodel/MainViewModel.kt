package com.sisnovate.newsapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisnovate.newsapp.network.model.news.NewsModelItem
import com.sisnovate.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {
    // create a [LiveData] here
    private val _newsList = MutableLiveData<List<NewsModelItem>>()
    val newsList: LiveData<List<NewsModelItem>> = _newsList

    fun loadNews() {
        viewModelScope.launch {
            try {
                val response = newsRepository.getAllNews()
                if (response.isSuccessful) {
                    val news = response.body()
                    news?.let { _newsList.value = it }
                } else {
                    // handle error
                    Log.d("GOT_ERROR", response.message())
                }
            } catch (e: Exception) {
                // handle error
                Log.d("GOT_EXCEPTION", e.message.toString())
            }
        }
    }
}