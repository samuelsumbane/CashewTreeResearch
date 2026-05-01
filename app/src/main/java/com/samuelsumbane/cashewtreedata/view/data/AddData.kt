package com.samuelsumbane.cashewtreedata.view.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.domain.model.AgeIntervals
import com.samuelsumbane.cashewtreedata.domain.model.ProductionQuality
import com.samuelsumbane.cashewtreedata.domain.model.Research
import com.samuelsumbane.cashewtreedata.presentation.FormerViewModel
import com.samuelsumbane.cashewtreedata.presentation.InputName
import com.samuelsumbane.cashewtreedata.presentation.Labels
import com.samuelsumbane.cashewtreedata.presentation.ResearchViewModel
import com.samuelsumbane.cashewtreedata.repository.CashewTreeRepository
import com.samuelsumbane.cashewtreedata.repository.FormersRepo
import com.samuelsumbane.cashewtreedata.repository.convertLongToDateString
import com.samuelsumbane.cashewtreedata.view.famers.AddFormerScreen
import com.samuelsumbane.cashewtreedata.widgets.AddDataRow
import com.samuelsumbane.cashewtreedata.widgets.AppButton
import com.samuelsumbane.cashewtreedata.widgets.AppTextInput
import com.samuelsumbane.cashewtreedata.widgets.BackButton
import com.samuelsumbane.cashewtreedata.widgets.CancelAndSubmitButtonRow
import com.samuelsumbane.cashewtreedata.widgets.DropDownComponent
import com.samuelsumbane.cashewtreedata.widgets.NoDataFound
import com.samuelsumbane.cashewtreedata.widgets.SearchComponent
import com.samuelsumbane.cashewtreedata.widgets.showToast
import org.koin.androidx.compose.koinViewModel
import org.koin.java.KoinJavaComponent.getKoin

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

    val cashewViewModel: ResearchViewModel = koinViewModel()
    val researchUiState by cashewViewModel.researchUiState.collectAsState()

    val context = LocalContext.current

    var personalData by remember { mutableIntStateOf(0) }
    var location by remember { mutableStateOf("") }
    var fugicidaName by remember { mutableStateOf("") }
    var puliverizationMonth by remember { mutableStateOf("") }
    var productionYear by remember { mutableStateOf("") }
    var cashewTreeAge by remember { mutableStateOf("") }
    var productionQuality by remember { mutableStateOf(ProductionQuality.Low) }
    var producedQuantity by remember { mutableStateOf("") }
    var pricePerKG by remember { mutableStateOf("") }
    var wasPulverized by remember { mutableStateOf(false) }
    var deases by remember { mutableStateOf("") }

    var searchedValue by remember { mutableStateOf("") }

    var showMonthsDropDown by remember { mutableStateOf(false) }
    var showQualitiesDropDown by remember { mutableStateOf(false) }
    var showSelectDateDropDown by remember { mutableStateOf(false) }

    var formersColumnZindex by remember { mutableFloatStateOf(0f) }
    var formerName by remember { mutableStateOf("Selecione o agricultor") }
    var formerId by remember { mutableIntStateOf(0) }

    val navigator = LocalNavigator.currentOrThrow
    val cashewTreeRepo = CashewTreeRepository
    val formersRepo = FormersRepo
//    val formersList = formersRepo.formers
    val formersViewModel by remember { mutableStateOf(getKoin().get<FormerViewModel>()) }
    val formersList by formersViewModel.formersList.collectAsState()


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
                title = { Text(text = if (formersColumnZindex > 1f) "Selecione um agricultor" else "Adicionar dados") },
                navigationIcon = {
                    BackButton { navigator.pop() }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        floatingActionButton = {
            if (formersColumnZindex > 1f) {
                FloatingActionButton(
                    onClick = { navigator.push(AddFormerScreen()) }
                ) {
                    Icon(Icons.Filled.Add, "Add research data")
                }
            }

        },
        containerColor = Color.DarkGray,
        contentColor = Color.White,
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
                    .zIndex(1f)

                .background(Color.DarkGray),
            ) {

//                LazyColumn(
//                    modifier = Modifier
//                        .padding(top = 30.dp),
//                    verticalArrangement = Arrangement.spacedBy(20.dp)
//                ) {
                CustomLazyColumn {
                    items(1) {

                        DropDownComponent(
                            title = "Agricultor",
                            selectedOptionText = formerName,
                            errorText = researchUiState.errors[InputName.formerName]
                        ) {
                            formersColumnZindex = 2f
                        }

                        AddDataRow { wasPulverized = it }

                        AnimatedVisibility (wasPulverized) {
                            AppTextInput(
                                inputLabel = "Fugicida",
                                value = fugicidaName,
                            ) { fugicidaName = it }



                            DropDownComponent(
                                title = "Selecione o mês de pulverização",
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

                            AppTextInput(
                                inputLabel = "Fugicida usada no ano",
                                value = researchUiState.usedFugicidaPerYear?.toString() ?: "0.0",
                                errorText = "",
                                keyboardType = KeyboardType.Decimal
                            ) {
                                if (it.matches(Regex("^\\d*\\.?\\d*$"))) {
                                    cashewViewModel.fillResearchFoarm(usedFugicidaPerYear = it.toDouble())
                                }
                            }
                        }


                        DropDownComponent(
                            title = "Selecionar o ano",
                            selectedOptionText = productionYear,
                            errorText = researchUiState.errors[InputName.productionYear]
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

                        DropDownComponent(
                            title = "Idade do canjueiro",
                            selectedOptionText = cashewTreeAge
                        ) {
                            cashewViewModel.fillResearchFoarm(showAgeIntervalDropMenu = !researchUiState.showAgeIntervals)
                        }

                        if (researchUiState.showAgeIntervals) {
                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { cashewViewModel.fillResearchFoarm(showAgeIntervalDropMenu = false) }
                            ) {
                                AgeIntervals.entries.forEach {
                                    DropdownMenuItem(
                                        text = { Text(it.stringValue) },
                                        onClick = { cashewTreeAge = it.stringValue }
                                    )
                                }
                            }
                        }

                        DropDownComponent(
                            title = "Selecione a qualidade",
                            selectedOptionText = productionQuality.stringValue,
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
                            errorText = researchUiState.errors[InputName.producedQuantity],
                            keyboardType = KeyboardType.Number
                        ) {
                            if (it.matches(Regex("^\\d*\\.?\\d*$"))) {
                                producedQuantity = it
                            }
                        }

                        AppTextInput(
                            inputLabel = "Preço (kg)",
                            value = pricePerKG.toString(),
                            errorText = researchUiState.errors[InputName.pricePerKG],
                            keyboardType = KeyboardType.Decimal
                        ) {
                            if (it.matches(Regex("^\\d*\\.?\\d*$"))) {
                                pricePerKG = it
                            }
                        }

                        AppTextInput(
                            inputLabel = "Doenças",
                            value = deases,
                        ) { deases = it }

                        Spacer(Modifier.height(40.dp))

                        CancelAndSubmitButtonRow(
                            onCancelClicked = { navigator.pop() }
                        ) {
                            if (formerName.isBlank()) {
                                cashewViewModel.setError(InputName.formerName, Labels.RequiredFormer.text)
                                return@CancelAndSubmitButtonRow
                            } else {
                                cashewViewModel.removeError(InputName.formerName)
                            }

                            if (location.isBlank()) {
                                cashewViewModel.setError(InputName.location, Labels.RequiredLocation.text)
                                return@CancelAndSubmitButtonRow
                            } else {
                                cashewViewModel.removeError(InputName.location)
                            }

                            if (productionYear.toInt() <= 0) {
                                cashewViewModel.setError(InputName.productionYear, Labels.RequiredProducedYear.text)
                                return@CancelAndSubmitButtonRow
                            } else {
                                cashewViewModel.removeError(InputName.productionYear)
                            }

//                            if (cashewTreeAge <)
                            if ((pricePerKG.toDoubleOrNull() ?: 0.0) <= 0.0) {
                                cashewViewModel.setError(InputName.pricePerKG, Labels.RequiredProductPrice.text)
                                return@CancelAndSubmitButtonRow
                            } else {
                                cashewViewModel.removeError(InputName.pricePerKG)
                            }


                            cashewViewModel.addResearch(
                                formerId,
                                fugicidaName,
                                researchUiState.usedFugicidaPerYear,
                                puliverizationMonth,
                                productionYear,
                                cashewTreeAge,
                                productionQuality.stringValue,
                                producedQuantity = producedQuantity.toDoubleOrNull() ?: 0.0,
                                pricePerKG = pricePerKG.toDoubleOrNull() ?: 0.0,
                                wasPulverized,
                                deases
                            )

                            showToast(context, "Cadastro feito com sucesso.")


                            formerId = 0
                            location = ""
                            fugicidaName = ""
                            puliverizationMonth = ""
                            productionYear = ""
                            cashewTreeAge = ""
                            productionQuality = ProductionQuality.Low
                            producedQuantity = ""
                            pricePerKG = ""
                            wasPulverized = false
                            deases = ""

                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding()
                    .fillMaxSize()
                    .zIndex(formersColumnZindex)
                    .background(Color.DarkGray)
            ) {

                SearchComponent(searchedValue) { searchedValue = it }

                if (formersList.isEmpty()) {
                    NoDataFound()
                } else {
                    CustomLazyColumn(spacedBy = 12.dp) {
                        items(searchedFormers) {
                            FormerRowItem(
                                name = it.name,
                                age = convertLongToDateString(it.birthDay),
                                modifier = Modifier
                                    .clickable {
                                        formerName = it.name
                                        formerId = it.id
                                        formersColumnZindex = 0f
                                    }
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(Color.Green)
                    ,
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = { formersColumnZindex = 0f }
                    ) {
                        Text("Fechar")
                    }
                }
            }
        }
    }
}


@Composable
fun CustomLazyColumn(
    spacedBy: Dp = 20.dp,
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.spacedBy(spacedBy)
    ) {
        content()
    }
}