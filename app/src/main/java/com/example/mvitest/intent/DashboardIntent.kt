package com.example.mvitest.intent

/**
 * Creator: Javohir Oromov
 * Date: 08/07/25
 * Project: MVI Test
 * Javohir's MacBook Air
 */

sealed class DashboardIntent {
    data object FetchBook: DashboardIntent()
}