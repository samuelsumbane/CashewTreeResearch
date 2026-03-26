package com.samuelsumbane.cashewtreedata

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.view.data.AddDataScreen
import com.samuelsumbane.cashewtreedata.view.data.ViewCashDataScreen
import com.samuelsumbane.cashewtreedata.view.famers.ViewFarmersPage
import com.samuelsumbane.cashewtreedata.view.famers.ViewFormersScreen
import com.samuelsumbane.cashewtreedata.widgets.AppButton
import com.samuelsumbane.cashewtreedata.widgets.BackButton

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        HomePage()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val navigator = LocalNavigator.currentOrThrow
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.DarkGray
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.DarkGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
            ) {
                AppButton("Cadastrar") { navigator.push(ViewCashDataScreen()) }
                AppButton("Agricultores") { navigator.push(ViewFormersScreen()) }
                AppButton("Exportar") { }
            }
        }
    }
}