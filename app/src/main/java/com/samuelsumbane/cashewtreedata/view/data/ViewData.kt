package com.samuelsumbane.cashewtreedata.view.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.domain.model.Utils.exportToCSVWithMediaStore
import com.samuelsumbane.cashewtreedata.presentation.Labels
import com.samuelsumbane.cashewtreedata.presentation.ResearchViewModel
import com.samuelsumbane.cashewtreedata.widgets.BackButton
import com.samuelsumbane.cashewtreedata.widgets.ExportDataButton
import com.samuelsumbane.cashewtreedata.widgets.NoDataFound
import com.samuelsumbane.cashewtreedata.widgets.SearchComponent
import com.samuelsumbane.cashewtreedata.widgets.showToast
import org.koin.androidx.compose.koinViewModel
import org.koin.java.KoinJavaComponent.getKoin

class ViewCashDataScreen : Screen {
    @RequiresApi(Build.VERSION_CODES.Q)
    @Composable
    override fun Content() {
        ViewCashewData()
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewCashewData() {

    var searchedDataValue by remember { mutableStateOf("") }

    val cashewTreeViewModel: ResearchViewModel = koinViewModel()
    val researchData by cashewTreeViewModel.researchs.collectAsState()

    val navigator = LocalNavigator.currentOrThrow
    val context = LocalContext.current

    val searchedData = remember (researchData, searchedDataValue) {
        if (searchedDataValue.isBlank()) {
            researchData
        } else {
            researchData.filter {
                it.farmer.name.contains(searchedDataValue, ignoreCase = true) ||
                it.research.productionYear.contains(searchedDataValue)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Visualizar dados") },
                navigationIcon = {
                    BackButton { navigator.pop() }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                actions = {
                    ExportDataButton {
                        exportToCSVWithMediaStore(
                            context,
                            searchedData,
                            str = "Nome,Genero,Ano_de_producao,Houve_pulverizacao,Fungicida,Fungicida_usada_por_ano,Unidade,Mes_de_pulverizacao,Idade_do_canjueiro,Qualidade_da_producao,Qugantidade_produzida,Preco_por_kg,doencas\n", outputFileName = "Cajus_dados.csv")
                        showToast(context, Labels.DataExportDone.text)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigator.push(AddDataScreen()) }
            ) {
                Icon(Icons.Filled.Add, "Add research data")
            }
        },
        containerColor = Color.DarkGray,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {

            if (researchData.isEmpty()) {
                NoDataFound()
            } else {
                SearchComponent(
                    value = searchedDataValue,
                    placeholder = "Pesquisar pelo nome ou ano"
                ) { searchedDataValue = it }

                LazyColumn(
                    modifier = Modifier.padding(top = 45.dp)
                ) {
                    items(searchedData) {
                        RowItem(it.farmer.name, it.research.productionYear) {
                            navigator.push(EachResearchScreen(it))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BasicRowItem(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(46.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) { content() }
}
@Composable
fun RowItem(
    name: String,
    location: String,
    onClick: () -> Unit
) {
    BasicRowItem(
        modifier = Modifier
            .clickable { onClick() }
    ) {
        ItemText(name)
        ItemText(location)
    }
}

@Composable
fun FormerRowItem(name: String, age: String, modifier: Modifier = Modifier) {
    BasicRowItem(modifier = modifier) {
        ItemText(name)
        ItemText(age.toString())
    }
}

@Composable
fun ItemText(text: String) {
    Text(text, color = Color.Black, modifier = Modifier.padding(10.dp))
}

