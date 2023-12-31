package com.centaq.homecontrol.ui.nav

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItemState(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasBadge: Boolean,
    val messages: Int
)
