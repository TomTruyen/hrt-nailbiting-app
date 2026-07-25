package com.tomtruyen.cusp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.cusp.data.local.CheckInEntity
import com.tomtruyen.cusp.data.repository.CheckInRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: CheckInRepository) : ViewModel() {
    val checkIns: StateFlow<List<CheckInEntity>> = repository.getAllCheckIns()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun logUrgeResisted() {
        viewModelScope.launch {
            repository.insertCheckIn(
                isBite = false,
                urgeStrength = 0,
                activity = null,
                feeling = null
            )
        }
    }

    fun logCaughtBiting(urgeStrength: Int, activity: String, feeling: String) {
        viewModelScope.launch {
            repository.insertCheckIn(
                isBite = true,
                urgeStrength = urgeStrength,
                activity = activity,
                feeling = feeling
            )
        }
    }
}
