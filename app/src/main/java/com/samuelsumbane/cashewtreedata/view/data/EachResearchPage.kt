package com.samuelsumbane.cashewtreedata.view.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.domain.model.ResearchWithFarmer
import com.samuelsumbane.cashewtreedata.widgets.BackButton
import com.samuelsumbane.cashewtreedata.widgets.TextItem


data class EachResearchScreen(val researchWithFarmer: ResearchWithFarmer)  : Screen {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        EachResearchPage(researchWithFarmer)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EachResearchPage(researchWithFarmer: ResearchWithFarmer) {
    val navigator = LocalNavigator.currentOrThrow

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da pesquisa") },
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
                TextItem("Nome", researchWithFarmer.farmer.name)
                TextItem("Gênero", researchWithFarmer.farmer.genere)
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                TextItem("Mês de pulv", researchWithFarmer.research.puliverizationMonth)
                TextItem("Ano de produção", researchWithFarmer.research.productionYear)
                TextItem("Idade do cajueiro", researchWithFarmer.research.cashewTreeAge.toString())
                TextItem("Quali. produção", researchWithFarmer.research.productionQuality)
                TextItem("Qtd. produção", researchWithFarmer.research.producedQuantity.toString())
                TextItem("Preço", researchWithFarmer.research.pricePerKG.toString())
                TextItem("Pulverizado?", booleanString(researchWithFarmer.research.wasPulverized))
                TextItem("Doenças", researchWithFarmer.research.deases)
            }
        }
    }
}

fun booleanString (booleanValue: Boolean): String {
    return when (booleanValue) {
        true -> "Sim"
        false -> "Não"
    }
}