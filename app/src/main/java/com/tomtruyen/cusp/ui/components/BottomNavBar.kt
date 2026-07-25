package com.tomtruyen.cusp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.tomtruyen.cusp.R
import com.tomtruyen.cusp.ui.navigation.Screen

data class BottomNavItem(
    val title: String,
    val iconRes: Int,
    val screen: Screen
)

@Composable
fun BottomNavBar(
    navController: NavController,
    currentDestination: NavDestination?
) {
    val items = listOf(
        BottomNavItem("Home", R.drawable.ic_home, Screen.Home),
        BottomNavItem("Insights", R.drawable.ic_insights, Screen.Insights),
        BottomNavItem("Progress", R.drawable.ic_progress, Screen.Progress),
        BottomNavItem("Learn", R.drawable.ic_learn, Screen.Learn),
        BottomNavItem("Settings", R.drawable.ic_settings, Screen.Settings)
    )

    NavigationBar {
        items.forEach { item ->
            val route = item.screen::class.qualifiedName
            val selected = currentDestination?.hierarchy?.any { it.route == route } == true
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.screen) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
}
