package com.samuelsumbane.cashewtreedata.view.famers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.domain.model.Agricultor
import com.samuelsumbane.cashewtreedata.widgets.BackButton
import com.samuelsumbane.cashewtreedata.widgets.TextItem


data class EachFormerScreen(val former: Agricultor)  : Screen {
    @Composable
    override fun Content() {
        EachFormerPage(former)
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EachFormerPage(former: Agricultor) {
    val navigator = LocalNavigator.currentOrThrow

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    BackButton { navigator.pop() }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.DarkGray),
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .background(Color.Blue, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TextItem("Nome", former.name)
                TextItem("Idade", former.birthDay.toString())
//                TextItem()
            }
        }
    }
}