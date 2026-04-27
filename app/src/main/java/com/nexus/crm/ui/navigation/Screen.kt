package com.nexus.crm.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object Dashboard : Screen(
        route = "dashboard",
        title = "Dashboard",
        selectedIcon = Icons.Filled.Dashboard,
        unselectedIcon = Icons.Outlined.Dashboard
    )

    data object Contacts : Screen(
        route = "contacts",
        title = "Contacts",
        selectedIcon = Icons.Filled.People,
        unselectedIcon = Icons.Outlined.People
    )

    data object ContactDetail : Screen(
        route = "contacts/{contactId}",
        title = "Contact",
        selectedIcon = Icons.Filled.People,
        unselectedIcon = Icons.Outlined.People
    ) {
        fun createRoute(contactId: Long) = "contacts/$contactId"
    }

    data object Deals : Screen(
        route = "deals",
        title = "Deals",
        selectedIcon = Icons.Filled.Work,
        unselectedIcon = Icons.Outlined.Work
    )

    data object Tasks : Screen(
        route = "tasks",
        title = "Tasks",
        selectedIcon = Icons.Filled.CheckCircle,
        unselectedIcon = Icons.Outlined.CheckCircle
    )

    companion object {
        val bottomNavItems = listOf(Dashboard, Contacts, Deals, Tasks)
    }
}