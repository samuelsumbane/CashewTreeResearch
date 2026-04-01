package com.samuelsumbane.cashewtreedata.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(Icons.Default.Delete, contentDescription = "Delete")
    }
}

@Composable
fun EditBuutton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.Default.Edit, contentDescription = "Edit")
    }
}