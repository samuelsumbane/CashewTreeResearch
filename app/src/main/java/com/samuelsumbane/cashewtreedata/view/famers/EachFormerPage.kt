package com.samuelsumbane.cashewtreedata.view.famers

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.domain.model.Former
import com.samuelsumbane.cashewtreedata.presentation.FormerViewModel
import com.samuelsumbane.cashewtreedata.repository.convertLongToDateString
import com.samuelsumbane.cashewtreedata.widgets.BackButton
import com.samuelsumbane.cashewtreedata.widgets.TextItem
import org.koin.java.KoinJavaComponent.getKoin


data class EachFormerScreen(val former: Former)  : Screen {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        EachFormerPage(former)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EachFormerPage(former: Former) {
    val navigator = LocalNavigator.currentOrThrow
    val currentTimeMillis = System.currentTimeMillis()
    val farmerViewModel by remember { mutableStateOf(getKoin().get<FormerViewModel>())}

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dados do agricultor") },
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
                    .background(Color.LightGray, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TextItem("Nome", former.name)
                TextItem("Data de nascimento", convertLongToDateString(former.birthDay))
                TextItem("Idade", farmerViewModel.calculateAge(former.birthDay).toString())
//                TextItem()
            }
        }
    }
}