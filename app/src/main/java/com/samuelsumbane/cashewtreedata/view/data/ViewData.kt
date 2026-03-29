package com.samuelsumbane.cashewtreedata.view.data

import android.R.attr.data
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samuelsumbane.cashewtreedata.domain.model.Research
import com.samuelsumbane.cashewtreedata.presentation.ResearchViewModel
import com.samuelsumbane.cashewtreedata.repository.CashewTreeRepository
import com.samuelsumbane.cashewtreedata.widgets.BackButton
import com.samuelsumbane.cashewtreedata.widgets.NoDataFound
import com.samuelsumbane.cashewtreedata.widgets.SearchComponent
import com.samuelsumbane.cashewtreedata.widgets.showToast
import org.koin.java.KoinJavaComponent.getKoin
import java.io.File
import java.io.OutputStreamWriter
import com.samuelsumbane.cashewtreedata.R

class ViewCashDataScreen : Screen {
    @RequiresApi(Build.VERSION_CODES.Q)
    @Composable
    override fun Content() {
        ViewCashewData()
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewCashewData() {

    var searchedDataValue by remember { mutableStateOf("") }

    val cashewTreeViewModel by remember { mutableStateOf(getKoin().get<ResearchViewModel>()) }

    val researchData by cashewTreeViewModel.researchs.collectAsState()


    val navigator = LocalNavigator.currentOrThrow
    val context = LocalContext.current

    val searchedData = remember (researchData, searchedDataValue) {
        if (searchedDataValue.isBlank()) {
            researchData
        } else {
            researchData.filter { it.formerId == 0 }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Visualizar dados") },
                navigationIcon = {
                    BackButton { navigator.pop() }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                actions = {
                    IconButton(
                        onClick = {
                            exportToCSVWithMediaStore(context, searchedData)
                            showToast(context, "O arquivo foi salvo com sucesso na pasta de downloads.")
                        }
                    ) {
                        Icon(painterResource(R.drawable.foldersymlinkfill), contentDescription = "export data")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigator.push(AddDataScreen()) }
            ) {
                Icon(Icons.Filled.Add, "Add research data")
            }
        },
        containerColor = Color.DarkGray,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {

            if (researchData.isEmpty()) {
                NoDataFound()
            } else {
                SearchComponent(
                    value = searchedDataValue
                ) { searchedDataValue = it }

                LazyColumn(
                    modifier = Modifier.padding(top = 45.dp)
                ) {
                    items(searchedData) {
                        RowItem(it.location, it.productionYear, "12:00")
                    }
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
            .background(Color.LightGray, RoundedCornerShape(8.dp)),
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
fun FormerRowItem(name: String, age: String, modifier: Modifier = Modifier) {
    BasicRowItem(modifier = modifier) {
        ItemText(name)
        ItemText(age.toString())
    }
}

@Composable
fun ItemText(text: String) {
    Text(text, color = Color.Black, modifier = Modifier.padding(10.dp))
}

@RequiresApi(Build.VERSION_CODES.Q)
fun exportToCSVWithMediaStore(context: Context, data: List<Research>) {
    val resolver = context.contentResolver

    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "dados.csv")
        put(MediaStore.MediaColumns.MIME_TYPE, "text/csv")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
    }

    val uri = resolver.insert(
        MediaStore.Downloads.EXTERNAL_CONTENT_URI,
        contentValues
    )

    uri?.let {
        resolver.openOutputStream(it)?.use { outputStream ->
            OutputStreamWriter(outputStream).use { writer ->
                writer.write("Nome,Idade\n")
                data.forEach {
                    writer.write("${it.formerId},${it.location}\n")
                }
            }
        }
    }
}