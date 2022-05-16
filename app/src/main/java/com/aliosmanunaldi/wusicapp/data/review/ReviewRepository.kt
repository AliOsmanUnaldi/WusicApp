package com.aliosmanunaldi.wusicapp.data.review

import com.aliosmanunaldi.wusicapp.Review
import com.aliosmanunaldi.wusicapp.data.login.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ReviewRepository {

    suspend fun setReview(review: Review): Flow<ReviewResponse?> {

        return flow { emit(api.setUserReview(review)) }

    }
}