package com.example.mvitest.data.remote

import com.example.mvitest.data.model.BookModel
import retrofit2.http.GET

/**
 * Creator: Javohir Oromov
 * Date: 08/07/25
 * Project: MVI Test
 * Javohir's MacBook Air
 */

interface ApiService {

    @GET("posts")
    suspend fun getBooks(): List<BookModel>
}