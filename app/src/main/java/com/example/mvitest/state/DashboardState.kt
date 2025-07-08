package com.example.mvitest.state

import com.example.mvitest.data.model.BookModel

/**
 * Creator: Javohir Oromov
 * Date: 08/07/25
 * Project: MVI Test
 * Javohir's MacBook Air
 */

sealed class DashboardState {
    data object Idle: DashboardState()
    data object Loading: DashboardState()
    data class Success(val books: List<BookModel>): DashboardState()
    data class Error(val error: String?): DashboardState()
}