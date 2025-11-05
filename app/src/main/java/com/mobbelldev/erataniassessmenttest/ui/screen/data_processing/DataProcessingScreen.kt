package com.mobbelldev.erataniassessmenttest.ui.screen.data_processing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mobbelldev.erataniassessmenttest.ui.screen.data_processing.model.Item
import com.mobbelldev.erataniassessmenttest.ui.screen.data_processing.repository.AgricultureRepository
import com.mobbelldev.erataniassessmenttest.ui.screen.data_processing.viewmodel.DataProcessingViewModel

@Composable
fun DataProcessingScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val repository = remember { AgricultureRepository(context = context) }
    val viewmodel = remember { DataProcessingViewModel(repository = repository) }

    val dataList by viewmodel.dataList.collectAsState()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(dataList) { data ->
            ItemCard(data = data)
        }
    }
}

@Composable
private fun ItemCard(
    modifier: Modifier = Modifier,
    data: Item
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nama Barang: ${data.nama_barang}")
            Text(text = "Stok Awal: ${data.stok_awal}")
            Text(text = "Tanggal Transaksi:")
            data.transaksi.forEach {
                Text(text = "- ${it.tanggal}: Masuk ${it.stok_masuk}, Keluar ${it.stok_keluar}")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Stok Akhir: ${data.stok_akhir}", fontWeight = FontWeight.Bold)
        }
    }
}