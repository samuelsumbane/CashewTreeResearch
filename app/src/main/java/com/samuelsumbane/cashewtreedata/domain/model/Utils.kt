package com.samuelsumbane.cashewtreedata.domain.model

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.samuelsumbane.cashewtreedata.view.data.booleanString
import java.io.OutputStreamWriter

object Utils {

    @RequiresApi(Build.VERSION_CODES.Q)
    fun <T> exportToCSVWithMediaStore(
        context: Context,
        databaseData: List<T>,
        str: String, // separated by "," and ends with \n
        outputFileName: String, // extersion: .csv
    ) {
        val resolver = context.contentResolver

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, outputFileName)
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
                    writer.write(str)
                    databaseData.forEach { data ->
                        when (data) {
                            is FinalResearch -> writer.write("${data.name},${data.genere},${data.productionYear},${booleanString(data.wasPulverized)},${data.fugicidaName},${data.usedFugicidaPerYear},${data.fungicidaUnity},${data.puliverizationMonth},${data.cashewTreeAge},${data.productionQuality},${data.producedQuantity},${data.pricePerKG},${data.deases}\n")
                            is FinalFormer -> writer.write("${data.name},${data.age},${data.schoolarity},${data.experienceYear},${data.genere},${data.productionArea},${data.location}\n")
                        }
                    }
                }
            }
        }
    }

    fun Double?.toStringFormat() = this.let { if (it == 0.0) "" else it.toString() } ?: "0"

}