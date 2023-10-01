package com.centaq.homecontrol

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.centaq.homecontrol.ui.theme.HomeControlTheme
import com.centaq.homecontrol.ui.UIGenerator
import com.centaq.homecontrol.ui.nav.NavGenerator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeControlTheme {
                generateContent(this,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun generateContent(activity: Activity?, modifier: Modifier = Modifier) {
    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        modifier = modifier
            ,
        bottomBar = {
            NavGenerator.generateNavBar(activity, selectedIndex, { selectedIndex = it }, NavGenerator.getNavDef())
        }
    ) {
        if (selectedIndex == 0) {
            UIGenerator.GenerateGroups(activity, UIGenerator.GetGroupDef())
        } else if (selectedIndex == 1) {
            Text("druga")
        } else {
            Text("Dupa");
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeControlTheme {
        generateContent(null, modifier = Modifier.fillMaxSize())
    }
}