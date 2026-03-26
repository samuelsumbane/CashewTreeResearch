package com.samuelsumbane.cashewtreedata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cafe.adriel.voyager.navigator.Navigator
import com.samuelsumbane.cashewtreedata.ui.theme.CashewTreeDataTheme
import com.samuelsumbane.cashewtreedata.view.data.AddDataScreen
import com.samuelsumbane.cashewtreedata.view.famers.AddFormerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CashewTreeDataTheme {
//                Navigator(ViewCashDataScreen())
//                Navigator(AddDataScreen())
//                Navigator(AddFormerScreen())
                Navigator(HomeScreen())
            }
        }
    }
}

