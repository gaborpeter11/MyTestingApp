package com.example.myapplication.activation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.be.NetworkModule
import com.example.myapplication.be.VersionRepository
import com.example.myapplication.be.VersionResponse
import com.example.myapplication.usecase.GetVersionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivationViewModel @Inject constructor(
    private val getVersionUseCase: GetVersionUseCase
) : ViewModel() {


    private val _versionState = MutableStateFlow<VersionResponse?>(null)
    val versionState: StateFlow<VersionResponse?> = _versionState

    fun getVersion(code: String) {

        viewModelScope.launch {
            try {   // add flow and state
                val response = getVersionUseCase(code)
                _versionState.value = response
            } catch (e: Exception) {    // handle specific error
                _versionState.value = null
            }
        }
    }

}
