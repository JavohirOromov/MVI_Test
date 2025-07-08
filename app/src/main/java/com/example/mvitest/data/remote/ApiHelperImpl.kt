package com.example.mvitest.data.remote
import com.example.mvitest.data.model.BookModel

/**
 * Creator: Javohir Oromov
 * Date: 08/07/25
 * Project: MVI Test
 * Javohir's MacBook Air
 */

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun getBooks(): List<BookModel> {
        return apiService.getBooks()
    }
}