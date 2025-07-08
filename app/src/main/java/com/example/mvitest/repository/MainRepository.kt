package com.example.mvitest.repository
import com.example.mvitest.data.model.BookModel
import com.example.mvitest.data.remote.ApiHelper

/**
 * Creator: Javohir Oromov
 * Date: 08/07/25
 * Project: MVI Test
 * Javohir's MacBook Air
 */

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getBooks(): List<BookModel>{
        return apiHelper.getBooks()
    }
}