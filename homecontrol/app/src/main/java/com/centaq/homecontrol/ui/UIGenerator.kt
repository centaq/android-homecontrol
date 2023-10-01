package com.centaq.homecontrol.ui

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.centaq.homecontrol.R
import com.centaq.homecontrol.SMSController
import com.centaq.homecontrol.MainActivity

public final class UIGenerator {
    companion object {
        @JvmStatic

        fun GetGroupDef(): List<UIGroupDefinition> {
            return listOf<UIGroupDefinition>(
                UIGroupDefinition(title = "Alarm - główny", rows = listOf(
                    UIRowDefinition(actions = listOf(
                        UIActionIconDefinition(description = "ARM", cmd = "ON", icon = R.drawable.baseline_lock_24),
                        UIActionIconDefinition(description = "DISARM", cmd = "OFF", icon = R.drawable.baseline_lock_open_24),
                        UIActionIconDefinition(description = "RESET", cmd = "RESET", icon = R.drawable.baseline_lock_reset_24),
                    ))
                )),
                UIGroupDefinition(title = "Alarm - sekcja 2", rows = listOf(
                    UIRowDefinition(actions = listOf(
                        UIActionIconDefinition(description = "ARM", cmd = "ON", icon = R.drawable.baseline_lock_24),
                        UIActionIconDefinition(description = "DISARM", cmd = "OFF", icon = R.drawable.baseline_lock_open_24),
                        UIActionIconDefinition(description = "RESET", cmd = "RESET", icon = R.drawable.baseline_lock_reset_24),
                    ))
                )),
                UIGroupDefinition(title = "Światło", rows = listOf(
                    UIRowDefinition(actions = listOf(
                        UIActionIconDefinition(description = "EXT ON", cmd = "EXTON", icon = R.drawable.lightbulb_fill),
                        UIActionIconDefinition(description = "EXT ON 60", cmd = "EXT60", icon = R.drawable.lightbulb_off_fill),
                        UIActionIconDefinition(description = "EXT ON 15", cmd = "EXT15", icon = R.drawable.lightbulb_off_fill),
                        UIActionIconDefinition(description = "EXT OFF", cmd = "EXTOFF", icon = R.drawable.lightbulb),
                        UIActionIconDefinition(description = "INT OFF", cmd = "INTOFF", icon = R.drawable.lightbulb),
                        UIActionIconDefinition(description = "LED OFF", cmd = "LEDOFF", icon = R.drawable.lightbulb),
                    ))
                )),
                UIGroupDefinition(title = "Alarm - config", rows = listOf(
                    UIRowDefinition(actions = listOf(
                        UIActionIconDefinition(description = "Głośny", cmd = "LOUD", icon = R.drawable.baseline_volume_up_24),
                        UIActionIconDefinition(description = "Wyciszony", cmd = "QUIET", icon = R.drawable.baseline_volume_off_24),
                        UIActionIconDefinition(description = "Test", cmd = "TEST", icon = R.drawable.baseline_safety_check_24),
                        UIActionIconDefinition(description = "Stan", cmd = "STAT", icon = R.drawable.baseline_safety_check_24),
                    ))
                )),
                UIGroupDefinition(title = "Woda", rows = listOf(
                    UIRowDefinition(actions = listOf(
                        UIActionIconDefinition(description = "Studnia ON", cmd = "WATERPUMPON", icon = R.drawable.bucket_fill),
                        UIActionIconDefinition(description = "Studnia OFF", cmd = "WATERPUMPOFF", icon = R.drawable.bucket),
                        UIActionIconDefinition(description = "Woda ON", cmd = "WATERON", icon = R.drawable.droplet_fill),
                        UIActionIconDefinition(description = "Woda OFF", cmd = "WATEROFF", icon = R.drawable.droplet),
                        UIActionIconDefinition(description = "Reset", cmd = "WATERRESET", icon = R.drawable.moisture),
                    ))
                ))
            )
        }
        @JvmStatic
        @Composable
        fun GenerateGroups(activity: Activity?, groups: List<UIGroupDefinition>) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFD3E6F3),
                                Color(0xFFDFEEF8)
                            )
                        )
                    )
            )
            {
                items(groups) { group ->
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFD3E6F3),
                                        Color(0xFFBBDAEE)
                                    )
                                )
                            )
                    ) {
                        item {
                            Column() {
                                Text(text = group.title)
                                for(row in group.rows) {
                                    Row() {

                                        for(action in row.actions) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize(),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                val c = LocalContext.current
                                                IconButton(
                                                    onClick = {
                                                        SMSController.sendSMS(c,activity, action.cmd);
                                                        Toast.makeText(c, "Komenda wysłana", Toast.LENGTH_LONG).show()
                                                    },
                                                    /*tint = Color.White,*/
                                                    modifier = Modifier
                                                        .size(64.dp)
                                                ) {
                                                    Icon(imageVector = ImageVector.vectorResource(action.icon),
                                                        contentDescription = action.description,
                                                        Modifier.size(32.dp))
                                                }
                                                Text(
                                                    action.description,
                                                    fontSize = TextUnit(12.0F, TextUnitType.Sp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}