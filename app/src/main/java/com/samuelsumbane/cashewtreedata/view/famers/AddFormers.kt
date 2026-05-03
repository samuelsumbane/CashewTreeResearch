package com.samuelsumbane.cashewtreedata.view.famers

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.domain.model.AgeIntervals
import com.samuelsumbane.cashewtreedata.domain.model.Utils.toStringFormat
import com.samuelsumbane.cashewtreedata.presentation.FormerViewModel
import com.samuelsumbane.cashewtreedata.presentation.InputName
import com.samuelsumbane.cashewtreedata.repository.FormersRepo
import com.samuelsumbane.cashewtreedata.repository.convertLongToDateString
import com.samuelsumbane.cashewtreedata.view.data.CustomLazyColumn
import com.samuelsumbane.cashewtreedata.widgets.AppTextInput
import com.samuelsumbane.cashewtreedata.widgets.BackButton
import com.samuelsumbane.cashewtreedata.widgets.CancelAndSubmitButtonRow
import com.samuelsumbane.cashewtreedata.widgets.DropDownComponent
import com.samuelsumbane.cashewtreedata.widgets.showToast
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


class AddFormerScreen : Screen {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        AddFormerPage()
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFormerPage() {
    val navigator = LocalNavigator.currentOrThrow

    val formRepo = FormersRepo
//    val formersViewModel by remember { mutableStateOf(getKoin().get<FormerViewModel>()) }
    val farmersViewModel: FormerViewModel = koinViewModel()
    val farmersState by farmersViewModel.formerState.collectAsState()

    var formGenere by remember { mutableStateOf(Genere.Other)}

    var formerBirthDate by remember { mutableLongStateOf(0L) }
    var formerName by remember { mutableStateOf("") }
    var formerExperience by remember { mutableStateOf("") }
    var showDatePickerDropDown by remember { mutableStateOf(false) }
    var showGenereDropDown by remember { mutableStateOf(false) }
    var showUserExperienceDropDown by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adicionar agricultor") },
                navigationIcon = {
                    BackButton { navigator.pop() }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        contentColor = Color.White,
        containerColor = Color.DarkGray,
        bottomBar = {
            CancelAndSubmitButtonRow(
                onCancelClicked = { navigator.pop() }
            ) {
                coroutineScope.launch {
                    if (formerName.isBlank()) {
                        farmersViewModel.setFieldError(InputName.formerName, "O nome do agricultor é obrigatório")
                        return@launch
                    } else {
                        farmersViewModel.removeFieldError(InputName.formerName)
                    }

                    if (farmersState.location.isBlank()) {
                        farmersViewModel.setFieldError(InputName.location, "A localização do agricultor é obrigatória")
                        return@launch
                    }

                    if (formerBirthDate == 0L) {
                        farmersViewModel.setFieldError(InputName.formerBirthDay, "A data de nascimento é obrigatória")
                        return@launch
                    } else {
                        farmersViewModel.removeFieldError(InputName.formerBirthDay)
                    }

                    //
                    farmersViewModel.removeFieldError(InputName.location)
                    farmersViewModel.addFormer(formerName, farmersState.location, formerBirthDate, farmersState.schoolarity, farmersState.productionArea, formerExperience, formGenere.genereName)

                    formerName = ""
                    formerBirthDate = 0L
                    formerExperience = ""
                    farmersViewModel.fillForm(location = "", schoolarity = "", productionArea = "")

                    showToast(context, "Novo agricultor adicionado com sucesso")
                }
            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.DarkGray),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray),
            ) {

                CustomLazyColumn {
                    items(1) {
                        AppTextInput(
                            inputLabel = "Nome do agricultor",
                            value = formerName,
                            errorText = farmersState.fieldsErrors[InputName.formerName]
                        ) { formerName = it }

                        AppTextInput(
                            inputLabel = "Localização",
                            value = farmersState.location,
                            errorText = farmersState.fieldsErrors[InputName.location]
                        ) { farmersViewModel.fillForm(location = it) }

                        AppTextInput(
                            inputLabel = "Escolaridade",
                            value = farmersState.schoolarity,
                            errorText = ""
                        ) { farmersViewModel.fillForm(schoolarity = it) }

                        DropDownComponent(
                            title = "Data de nascimento",
                            selectedOptionText = if (formerBirthDate != 0L) convertLongToDateString(
                                formerBirthDate
                            ) else "",
                            errorText = farmersState.fieldsErrors[InputName.formerBirthDay]
                        ) { showDatePickerDropDown = true }

                        if (showDatePickerDropDown) {
                            val datepickerState = rememberDatePickerState()
                            DatePickerDialog(
                                onDismissRequest = { showDatePickerDropDown = false },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            datepickerState.selectedDateMillis?.let {
                                                formerBirthDate = it
                                                showDatePickerDropDown = false
                                            }
                                        }
                                    ) {
                                        Text("Ok")
                                    }
                                }
                            ) {
                                DatePicker(state = datepickerState)
                            }
                        }


                        AppTextInput(
                            inputLabel = "Área de produção (ha)",
                            value = farmersState.productionArea,
                            errorText = "",
                        ) {
                            farmersViewModel.fillForm(productionArea = it)
                        }

                        DropDownComponent(
                            title = "Exp. Do Agri. (anos)",
                            selectedOptionText = formerExperience
                        ) {
                            showUserExperienceDropDown = !showUserExperienceDropDown
                        }

                        if (showUserExperienceDropDown) {
                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { showUserExperienceDropDown = false }
                            ) {
                                AgeIntervals.entries.forEach {
                                    DropdownMenuItem(
                                        text = { Text(it.stringValue) },
                                        onClick = {
                                            formerExperience = it.stringValue
                                            showUserExperienceDropDown = false
                                        }
                                    )
                                }
                            }
                        }

                        DropDownComponent(
                            title = "Genero",
                            selectedOptionText = formGenere.genereName
                        ) {
                            showGenereDropDown = true
                        }

                        if (showGenereDropDown) {
                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { showGenereDropDown = false }
                            ) {
                                Genere.entries.forEach { genere ->
                                    DropdownMenuItem(
                                        text = { Text(genere.genereName) },
                                        onClick = {
                                            formGenere = genere
                                            showGenereDropDown = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


enum class Genere(val genereName: String) {
    Male("Masculino"),
    Female("Femenino"),
    Other("Outro")
}