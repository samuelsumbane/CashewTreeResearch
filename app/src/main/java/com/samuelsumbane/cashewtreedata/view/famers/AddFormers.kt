package com.samuelsumbane.cashewtreedata.view.famers

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.domain.model.Agricultor
import com.samuelsumbane.cashewtreedata.presentation.FormerViewModel
import com.samuelsumbane.cashewtreedata.repository.FormersRepo
import com.samuelsumbane.cashewtreedata.repository.convertLongToDateString
import com.samuelsumbane.cashewtreedata.widgets.AppTextInput
import com.samuelsumbane.cashewtreedata.widgets.BackButton
import com.samuelsumbane.cashewtreedata.widgets.CancelAndSubmitButtonRow
import com.samuelsumbane.cashewtreedata.widgets.DropDownComponent
import com.samuelsumbane.cashewtreedata.widgets.showToast
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.getKoin


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
    val formersViewModel by remember { mutableStateOf(getKoin().get<FormerViewModel>()) }


    var formGenere by remember { mutableStateOf(Genere.Other)}

    var formerBirthDate by remember { mutableLongStateOf(0L) }
    var formerName by remember { mutableStateOf("") }
    var formerExperience by remember { mutableIntStateOf(0) }
    var showDatePickerDropDown by remember { mutableStateOf(false) }
    var showGenereDropDown by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

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
                Row(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Cadastrar agricultor", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                }

                AppTextInput(
                    inputLabel = "Nome do agricultor",
                    value = formerName
                ) { formerName = it }

                DropDownComponent(
                    title = "Data de nascimento",
                    selectedOptionText = if (formerBirthDate != 0L) convertLongToDateString(formerBirthDate) else ""
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
                    inputLabel = "Exp. Do Agri. (anos)",
                    value = formerExperience.toString(),
                    keyboardType = KeyboardType.Number
                ) { formerExperience = it.toInt() }

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

                CancelAndSubmitButtonRow(
                    onCancelClicked = { navigator.pop() }
                ) {
                    coroutineScope.launch {
                        formersViewModel.addFormer(formerName, formerBirthDate, formerExperience, formGenere.genereName)

                        formerName = ""
                        formerBirthDate = 0L
                        formerExperience = 0

                        showToast(context, "Novo agricultor adicionado com sucesso")
                    }
                }

            }
        }
    }
}


enum class Genere(val genereName: String) {
    Male("Masculino"),
    Female("Female"),
    Other("Outro")
}