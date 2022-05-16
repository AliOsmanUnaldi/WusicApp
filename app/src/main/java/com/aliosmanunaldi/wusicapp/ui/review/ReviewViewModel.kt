package com.aliosmanunaldi.wusicapp.ui.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliosmanunaldi.wusicapp.Review
import com.aliosmanunaldi.wusicapp.data.review.ReviewRepository
import com.aliosmanunaldi.wusicapp.data.review.ReviewResponse
import kotlinx.coroutines.launch

class ReviewViewModel(
    val repository: ReviewRepository
) : ViewModel() {

    val pageLiveData: MutableLiveData<ReviewPageViewState> = MutableLiveData()

    fun getPageLiveData(): LiveData<ReviewPageViewState> = pageLiveData

    fun setReview(review: Review) = viewModelScope.launch {
        try {
            repository.setReview(review).collect {
                pageLiveData.value = ReviewPageViewState(it)
            }
        } catch (e: Exception) {
            pageLiveData.value = ReviewPageViewState(
                ReviewResponse(
                    success = false,
                    message = "başarısız"
                )
            )
        }
    }
}