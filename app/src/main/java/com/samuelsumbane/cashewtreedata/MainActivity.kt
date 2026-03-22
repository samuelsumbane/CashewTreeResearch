package com.samuelsumbane.cashewtreedata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.samuelsumbane.cashewtreedata.ui.theme.CashewTreeDataTheme
import com.samuelsumbane.cashewtreedata.view.data.AddDataScreen
import com.samuelsumbane.cashewtreedata.view.data.ViewCashDataScreen
import com.samuelsumbane.cashewtreedata.view.data.ViewCashewData
import com.samuelsumbane.cashewtreedata.widgets.AppButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CashewTreeDataTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column(
//                        modifier = Modifier
//                            .padding(innerPadding)
//                    ) {
//                        AppButton("Cadastrar") { }
//                        AppButton("Agricultores") { }
//                        AppButton("Exportar") { }
//                    }
//                }

//                Navigator(ViewCashDataScreen())
                Navigator(AddDataScreen())
            }
        }
    }
}

