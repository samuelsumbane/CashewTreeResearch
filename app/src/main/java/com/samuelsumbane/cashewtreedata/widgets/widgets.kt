package com.samuelsumbane.cashewtreedata.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.samuelsumbane.cashewtreedata.R

@Composable
fun AppButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .padding(20.dp)
//            .fillMaxWidth()
            .height(45.dp),
    ) { Text(text) }
}


@Composable
fun AppRadioButton(
    selected: Boolean,
    onClick: () -> Unit
) {
    RadioButton(
        selected = selected,
        onClick = onClick,
        colors = RadioButtonDefaults.colors(
            selectedColor = Color.Yellow,
        )
    )
}


@Composable
fun AddDataRow(
    onChange: (Boolean) -> Unit
) {
    val options = listOf("Sim", "Não")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(modifier = Modifier.padding(start = 20.dp)) {

        Text("Pulverizado")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            options.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppRadioButton(selected = option == selectedOption) {
                        selectedOption = option
                        onChange(option == "Sim")
                    }
                    Text(option)
                }
            }
        }
    }
}

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.AutoMirrored.Default.ArrowBack,
            "Back")
    }
}

@Composable
fun ExportDataButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(painterResource(R.drawable.foldersymlinkfill), contentDescription = "export data")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownComponent(
    title: String,
    selectedOptionText: String,
    errorText: String? = null,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(title)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(12.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(selectedOptionText, modifier = Modifier.padding(start = 10.dp), color = Color.Black)
            IconButton(
                onClick = onClick
            ) {
                Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Open options", tint = Color.Black)
            }
        }

        errorText?.let { ErrorLabelText(it) }
    }
}

@Composable
fun CancelAndSubmitButtonRow(
    onCancelClicked: () -> Unit,
    onSubmitClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(top = 17.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedButton(
            onClick = onCancelClicked
        ) {
            Text("Cancelar")
        }
        AppButton(text = "Submeter") { onSubmitClicked() }
    }
}

@Composable
fun TextItem(key: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(key, color = Color.Black)
        Text(value, color = Color.Black)
    }
}

fun showToast(
    context: Context,
    text: String
) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}


@Composable
fun SearchComponent(
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
//                        .background(Color.Red)
        ,
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            prefix = {
                Icon(Icons.Filled.Search, contentDescription = "Search formers")
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@Composable
fun NoDataFound() {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color.Red)
        ,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Nenhum registro encontrado")
        }
    }
}

@Composable
fun ErrorLabelText(errorText: String) {
    Text(
        text = errorText,
        style = MaterialTheme.typography.bodySmall,
        color = Color.Red
    )
}