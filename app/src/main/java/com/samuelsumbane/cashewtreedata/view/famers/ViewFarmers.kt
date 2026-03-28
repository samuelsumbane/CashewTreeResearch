package com.samuelsumbane.cashewtreedata.view.famers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.presentation.FormerViewModel
import com.samuelsumbane.cashewtreedata.repository.FormersRepo
import com.samuelsumbane.cashewtreedata.view.data.BasicRowItem
import com.samuelsumbane.cashewtreedata.view.data.ItemText
import com.samuelsumbane.cashewtreedata.widgets.BackButton
import org.koin.java.KoinJavaComponent.getKoin

class ViewFormersScreen : Screen {
    @Composable
    override fun Content() {
        ViewFarmersPage()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewFarmersPage() {
    val navigator = LocalNavigator.currentOrThrow
//    val formersRepo = FormersRepo
    val formerViewModel by remember { mutableStateOf(getKoin().get<FormerViewModel>()) }
    val formersData by formerViewModel.formersList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    BackButton { navigator.pop() }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.DarkGray,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigator.push(AddFormerScreen()) }
            ) {
                Icon(Icons.Filled.Add, "Add research data")
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {

            Row(
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Agricultores", fontWeight = FontWeight.Bold, fontSize = 25.sp)
            }

            LazyColumn {
                items(formersData) {
                    BasicRowItem(
                        modifier = Modifier
                            .clickable {
                                navigator.push(EachFormerScreen(it))
                            }
                    ) {
                        ItemText(it.name)
                    }
                }
            }
        }
    }
}