package com.tomtruyen.cusp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tomtruyen.cusp.ui.components.BottomNavBar
import com.tomtruyen.cusp.ui.navigation.Screen
import com.tomtruyen.cusp.ui.screens.HomeScreen
import com.tomtruyen.cusp.ui.screens.InsightsScreen
import com.tomtruyen.cusp.ui.screens.ProgressScreen
import com.tomtruyen.cusp.ui.screens.UrgeDetectedScreen
import com.tomtruyen.cusp.ui.screens.AfterInterruptScreen
import com.tomtruyen.cusp.ui.screens.WeeklyReviewScreen
import com.tomtruyen.cusp.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavBar(
                            navController = navController,
                            currentDestination = currentDestination
                        )
                    }
                ) { innerPadding ->
                    val sharedViewModel: com.tomtruyen.cusp.ui.screens.SharedViewModel = org.koin.androidx.compose.koinViewModel()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Screen.Home> { 
                            HomeScreen(
                                onNoticeUrge = { navController.navigate(Screen.UrgeDetected) },
                                onCaughtBiting = { navController.navigate(Screen.AfterInterrupt) },
                                viewModel = sharedViewModel
                            ) 
                        }
                        composable<Screen.Insights> { 
                            InsightsScreen(
                                onNavigateToWeeklyReview = { navController.navigate(Screen.WeeklyReview) },
                                viewModel = sharedViewModel
                            ) 
                        }
                        composable<Screen.Progress> { 
                            ProgressScreen(viewModel = sharedViewModel) 
                        }
                        composable<Screen.UrgeDetected> { 
                            UrgeDetectedScreen(
                                onBack = { navController.popBackStack() },
                                onResisted = { 
                                    sharedViewModel.logUrgeResisted()
                                    navController.navigate(Screen.AfterInterrupt) 
                                }
                            ) 
                        }
                        composable<Screen.AfterInterrupt> { 
                            AfterInterruptScreen(
                                onSave = { 
                                    sharedViewModel.logCaughtBiting(2, "Working", "Bored")
                                    navController.navigate(Screen.Home) { popUpTo(Screen.Home) { inclusive = false } } 
                                }
                            ) 
                        }
                        composable<Screen.WeeklyReview> {
                            WeeklyReviewScreen(
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}