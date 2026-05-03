package com.samuelsumbane.cashewtreedata.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun InputFieldSorounder(
    label: String,
    modifier: Modifier,
    errorText: String?,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.padding(2.dp)
    ) {
        Text(label, modifier = Modifier.padding(bottom = 10.dp))
        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .border(1.dp, Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp),
        ) { content() }
        errorText?.let { ErrorLabelText(it) }
    }
}

@Composable
fun AppTextInput(
    inputLabel: String,
    value: String,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChanged: (String) -> Unit
) {
    InputFieldSorounder(
        label = inputLabel,
        modifier = modifier.padding(10.dp),
        errorText = errorText
    ) {
        BasicTextField(
            value = value,
            onValueChange = { onValueChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.White),
            cursorBrush = SolidColor(Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
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

@Composable
fun DoubleInputField(
    label: String,
    value: Double?,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    onValueChange: (Double?) -> Unit,
) {
    var fieldValue by remember {
        mutableStateOf(TextFieldValue(value?.toString() ?: ""))
    }

    InputFieldSorounder(
        label = label,
        modifier = modifier.padding(10.dp),
        errorText = errorText
    ) {
        BasicTextField(
            value = fieldValue,
            onValueChange = { new ->
                val raw = new.text

                val cleaned = when {
                    fieldValue.text == "0.0" && raw.length > 3 -> raw.replace("0.0", "")
                    raw.matches(Regex("0[0-9]+.*")) -> raw.trimStart('0').ifEmpty { "0" }
                    else -> raw
                }

                fieldValue = new.copy(
                    text = cleaned,
                    selection = TextRange(cleaned.length) // cursor on final
                )

                onValueChange(cleaned.replace(",", ".").toDoubleOrNull())
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            textStyle = TextStyle(color = Color.White),
            cursorBrush = SolidColor(Color.White),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}