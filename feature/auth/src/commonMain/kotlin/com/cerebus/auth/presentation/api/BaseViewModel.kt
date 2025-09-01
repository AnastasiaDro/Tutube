package com.cerebus.auth.presentation.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

open class BaseViewModel : ViewModel() {
    val scope = this.viewModelScope
}