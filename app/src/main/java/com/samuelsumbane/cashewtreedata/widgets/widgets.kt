package com.samuelsumbane.cashewtreedata.widgets

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
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
fun AddDataRow() {
    val options = listOf("Sim", "Não")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(modifier = Modifier.padding(start = 10.dp)) {

        Text("Pulverizado")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            options.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppRadioButton(selected = option == selectedOption) {
                        selectedOption = option
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownComponent(
    title: String,
    selectedOptionText: String,
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
            Text(selectedOptionText, modifier = Modifier.padding(start = 10.dp))
            IconButton(
                onClick = {}
            ) {
                Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Open options")
            }
        }
    }
}