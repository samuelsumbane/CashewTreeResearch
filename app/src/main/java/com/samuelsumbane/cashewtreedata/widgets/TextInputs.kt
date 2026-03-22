package com.samuelsumbane.cashewtreedata.widgets

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppTextInput(
    inputLabel: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Text(inputLabel, modifier = Modifier.padding(bottom = 10.dp))

        Column(
            modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(12.dp))
                .padding(16.dp),
        ) {
            BasicTextField(
                value = value,
                onValueChange = { onValueChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
//            .height(70.dp)
//            .background(Color.Red)
    ) {
        AppTextInput("Nome do agricultor","Roma, centro do universo") { }
    }
}