package com.samuelsumbane.cashewtreedata.view.data

import androidx.compose.foundation.background
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.repository.CashewTreeRepository


class ViewCashDataScreen : Screen {
    @Composable
    override fun Content() {
        ViewCashewData()
    }
}

@Composable
fun ViewCashewData() {

    val cashewTreeRepository = CashewTreeRepository
    cashewTreeRepository.addCashew()
    println("os dados sao: ${cashewTreeRepository.cashewData}")

    val navigator = LocalNavigator.currentOrThrow

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigator.push(AddDataScreen()) }
            ) {
                Icon(Icons.Filled.Add, "Add research data")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Red)
        ) {
            LazyColumn {
                items(cashewTreeRepository.cashewData) {
                    RowItem(it.location, it.productionYear, "12:00")
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
            .background(Color.Green, RoundedCornerShape(8.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) { content() }
}
@Composable
fun RowItem(local: String, date: String, time: String) {
    BasicRowItem {
        ItemText(local)
        ItemText(date)
        ItemText(time)
    }
}

@Composable
fun FormerRowItem(name: String, age: Int) {
    BasicRowItem {
        ItemText(name)
        ItemText(age.toString())
    }
}

@Composable
fun ItemText(text: String) {
    Text(text, color = Color.Black, modifier = Modifier.padding(10.dp))
}