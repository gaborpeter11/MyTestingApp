package com.example.myapplication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    private val _scratchCardCode = mutableStateOf("")
    val scratchCardCode: State<String> = _scratchCardCode

    private val _scratchCard = mutableStateOf(false)
    val scratchCard: State<Boolean> = _scratchCard

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private var job: Job? = null

    fun scratchCard(init: Boolean = false) {
        if (!init) {
            _loading.value = true
            job = viewModelScope.launch {
                delay(2000L)
                _scratchCard.value = !_scratchCard.value
                _scratchCardCode.value = UUID.randomUUID().toString()
                _loading.value = false
            }
        } else {
            _scratchCard.value = !_scratchCard.value
            _scratchCardCode.value = UUID.randomUUID().toString()
        }
    }

    fun onBackClicked() {
        job?.cancel()
        _loading.value = false
    }

}
