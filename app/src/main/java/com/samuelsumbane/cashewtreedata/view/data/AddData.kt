package com.samuelsumbane.cashewtreedata.view.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.domain.model.ProductionQuality
import com.samuelsumbane.cashewtreedata.repository.CashewTreeRepository
import com.samuelsumbane.cashewtreedata.repository.FormersRepo
import com.samuelsumbane.cashewtreedata.widgets.AddDataRow
import com.samuelsumbane.cashewtreedata.widgets.AppButton
import com.samuelsumbane.cashewtreedata.widgets.AppTextInput
import com.samuelsumbane.cashewtreedata.widgets.BackButton
import com.samuelsumbane.cashewtreedata.widgets.DropDownComponent

class AddDataScreen : Screen {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        AddResearchData()
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddResearchData() {

    val months = listOf("Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro")

    var personalData by remember { mutableIntStateOf(0) }
    var location by remember { mutableStateOf("") }
    var fugicidaName by remember { mutableStateOf("") }
    var puliverizationMonth by remember { mutableStateOf("") }
    var productionYear by remember { mutableStateOf("") }
    var cashewTreeAge by remember { mutableIntStateOf(0) }
    var productionQuality by remember { mutableStateOf(ProductionQuality.Low) }
    var producedQuantity by remember { mutableDoubleStateOf(0.0) }
    var pricePerKG by remember { mutableDoubleStateOf(0.0) }
    var wasPulverized by remember { mutableStateOf(true) }
    var deases by remember { mutableStateOf("") }

    var searchedValue by remember { mutableStateOf("") }

    var showMonthsDropDown by remember { mutableStateOf(false) }
    var showQualitiesDropDown by remember { mutableStateOf(false) }
    var showSelectDateDropDown by remember { mutableStateOf(false) }

    val navigator = LocalNavigator.currentOrThrow
    val cashewTreeRepo = CashewTreeRepository
    val formersRepo = FormersRepo
    val formersList = formersRepo.formers

    val searchedFormers = remember(formersList, searchedValue) {
        if (searchedValue.isBlank()) {
            formersList
        } else {
            formersList.filter { it.name.contains(searchedValue, ignoreCase = true) }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    BackButton { navigator.pop() }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.DarkGray
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.DarkGray),
        ) {

            Column(
                modifier = Modifier
//                .padding(innerPadding)
                    .fillMaxSize()
//                .background(Color.DarkGray),
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Adicionar dados", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                }

//                LazyColumn(
//                    modifier = Modifier
//                        .padding(top = 30.dp),
//                    verticalArrangement = Arrangement.spacedBy(20.dp)
//                ) {
                CustomLazyColumn {
                    items(1) {
                        AppTextInput(
                            inputLabel = "Localização",
                            value = location
                        ) { location = it }

                        AppTextInput(
                            inputLabel = "Fugicida",
                            value = fugicidaName
                        ) { fugicidaName = it }

                        DropDownComponent(
                            title = "Select mes",
                            selectedOptionText = puliverizationMonth
                        ) {
                            showMonthsDropDown = !showMonthsDropDown
                        }

                        if (showMonthsDropDown) {
                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { showMonthsDropDown = false }
                            ) {
                                months.forEach { month ->
                                    DropdownMenuItem(
                                        text = { Text(month) },
                                        onClick = {
                                            puliverizationMonth = month
                                            showMonthsDropDown = false
                                        }
                                    )
                                }
                            }
                        }


                        DropDownComponent(
                            title = "Selecionar o ano",
                            selectedOptionText = productionYear
                        ) { showSelectDateDropDown = true }

                        if (showSelectDateDropDown) {
                            val years = (1990..2026).toList().reversed()

                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { showSelectDateDropDown = false }
                            ) {
                                years.forEach { year ->
                                    DropdownMenuItem(
                                        text = { Text(year.toString()) },
                                        onClick = {
                                            productionYear = year.toString()
                                            showSelectDateDropDown = false
                                        }
                                    )
                                }
                            }
                        }

                        AppTextInput(
                            inputLabel = "Idade do canjueiro",
                            value = cashewTreeAge.toString(),
                            keyboardType = KeyboardType.Number
                        ) { cashewTreeAge = it.toInt() }

                        DropDownComponent(
                            title = "Selecione a qualidade",
                            selectedOptionText = productionQuality.stringValue
                        ) {
                            showQualitiesDropDown = !showQualitiesDropDown
                        }

                        if (showQualitiesDropDown) {
                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { showQualitiesDropDown = false },

                                ) {
                                listOf(
                                    ProductionQuality.Low, ProductionQuality.Medium,
                                    ProductionQuality.High
                                ).forEach { quality ->
                                    DropdownMenuItem(
                                        text = { Text(quality.stringValue) },
                                        onClick = {
                                            productionQuality = quality
                                            showQualitiesDropDown = false
                                        }
                                    )
                                }
                            }
                        }

                        AppTextInput(
                            inputLabel = "Quantidade da produção",
                            value = producedQuantity.toString(),
                            keyboardType = KeyboardType.Number
                        ) {
                            producedQuantity = it.toDouble()
                        }


                        AppTextInput(
                            inputLabel = "Preço (kg)",
                            value = pricePerKG.toString(),
                            keyboardType = KeyboardType.Decimal
                        ) { pricePerKG = it.toDouble() }

                        AddDataRow()

                        AppTextInput(
                            inputLabel = "Doenças",
                            value = deases
                        ) { deases = it }

                        Row(
                            modifier = Modifier
                                .padding(top = 17.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = {

                                }
                            ) {
                                Text("Cancelar")
                            }
                            AppButton(text = "Submeter") {
                                println("o formulario: personal: $personalData, loc: $location  fug: $fugicidaName pul: $wasPulverized, pulverizationMonth:$puliverizationMonth, proY: $productionYear cAge: $cashewTreeAge proQlty: $productionQuality prQtty: $producedQuantity priceKg: $pricePerKG pulv: $wasPulverized deases: $deases")
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding()
                    .fillMaxSize()
                    .zIndex(2f)
                    .background(Color.Black.copy(alpha = 0.9f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(Color.Red)
                    ,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = searchedValue,
                        onValueChange = { searchedValue = it },
                        prefix = {
                            Icon(Icons.Filled.Search, contentDescription = "Search formers")
                        },
                        modifier = Modifier.fillMaxWidth(0.8f),
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                CustomLazyColumn {
                items(searchedFormers) {
                        FormerRowItem(it.name, it.age)
                    }
                }
            }
        }
    }
}


@Composable
fun CustomLazyColumn(
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        content()
    }
}