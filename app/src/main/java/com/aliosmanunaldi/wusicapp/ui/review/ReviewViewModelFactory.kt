package com.aliosmanunaldi.wusicapp.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliosmanunaldi.wusicapp.data.review.ReviewRepository

@Suppress("UNCHECKED_CAST")
class ReviewViewModelFactory(private val repository: ReviewRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReviewViewModel(repository) as T
    }
}