package com.example.twitchclone.rv_activity

import com.example.twitchclone.data.Transmission

data class TransmissionUIState(
    val isLoading: Boolean = false,
    val isListActive: Boolean = true,
    val errorMessage: String? = null,
    val transmissions: List<Transmission> = emptyList(),
    val deactivationTime: Int = 3
)