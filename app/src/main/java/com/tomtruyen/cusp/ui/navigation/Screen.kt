package com.tomtruyen.cusp.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Home : Screen
    
    @Serializable
    data object Insights : Screen
    
    @Serializable
    data object Progress : Screen
    
    @Serializable
    data object UrgeDetected : Screen
    
    @Serializable
    data object AfterInterrupt : Screen
    
    @Serializable
    data object WeeklyReview : Screen
}
