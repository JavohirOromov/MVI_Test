package com.example.mvitest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvitest.intent.DashboardIntent
import com.example.mvitest.repository.MainRepository
import com.example.mvitest.state.DashboardState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

/**
 * Creator: Javohir Oromov
 * Date: 08/07/25
 * Project: MVI Test
 * Javohir's MacBook Air
 */

class DashboardViewModel(private val repository: MainRepository): ViewModel() {
    val userIntent = Channel<DashboardIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<DashboardState>(DashboardState.Idle)
    val state: StateFlow<DashboardState> get() = _state


    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{
                when(it){
                    is DashboardIntent.FetchBook -> fetchBooks()
                }
            }
        }
    }

    private fun fetchBooks(){
        viewModelScope.launch {
            _state.value = DashboardState.Loading
            _state.value = try {
                DashboardState.Success(repository.getBooks())
            }catch (e: Exception){
                DashboardState.Error(e.localizedMessage)
            }
        }
    }
}