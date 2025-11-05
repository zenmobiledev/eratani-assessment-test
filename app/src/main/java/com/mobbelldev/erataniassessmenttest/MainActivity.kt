package com.mobbelldev.erataniassessmenttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mobbelldev.erataniassessmenttest.navigation.AppNavHost
import com.mobbelldev.erataniassessmenttest.ui.theme.ErataniAssessmentTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ErataniAssessmentTestTheme {
                AppNavHost()
            }
        }
    }
}