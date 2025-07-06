package com.example.financialmanager.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel : ViewModel() {
    private val _darkMode = MutableStateFlow(false)
    val darkMode = _darkMode.asStateFlow()

    fun toggleDarkMode(enabled: Boolean) {
        _darkMode.value = enabled
    }
}
