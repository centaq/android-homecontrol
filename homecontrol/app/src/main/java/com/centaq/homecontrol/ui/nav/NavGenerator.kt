package com.centaq.homecontrol.ui.nav

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.centaq.homecontrol.ui.UIGroupDefinition
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

public final class NavGenerator {
    companion object {
        @JvmStatic

        fun getNavDef(): List<NavItemState> {
            return listOf(
                NavItemState(
                    title = "Home",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    hasBadge = false,
                    messages = 0
                ),
                NavItemState(
                    title = "Notifications",
                    selectedIcon = Icons.Filled.Notifications,
                    unselectedIcon = Icons.Outlined.Notifications,
                    hasBadge = false,
                    messages = 12
                )
            )
        }

        @OptIn(ExperimentalMaterial3Api::class)
        @JvmStatic
        @Composable
        fun generateNavBar(activity: Activity?, selectedIndex: Int, onselectedIndexChange: (Int) -> Unit, items: List<NavItemState>) {
            NavigationBar(
                Modifier
                    .padding(6.dp)
                    .clip(RoundedCornerShape(10.dp)),
                containerColor = Color(0xFFACCFE6)
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { onselectedIndexChange(index) },
                        icon = {
                            BadgedBox(badge = {
                                if (item.hasBadge) Badge {}
                                if (item.messages != 0) Badge {
                                    Text(text = "${item.messages}")
                                }
                            }) {
                                Icon(
                                    imageVector = if (selectedIndex == index) item.selectedIcon
                                    else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        },
                        label = { Text(text = item.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFACCFE6),
                            selectedTextColor = Color(0xFF002741),
                            indicatorColor = Color(0xFF3290CC)
                        )
                    )
                }
            }
        }
    }
}