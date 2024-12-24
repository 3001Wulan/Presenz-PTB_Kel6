package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LaporanAbsensiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LaporanAbsensiApp()
            }
        }
    }
}

@Composable
fun LaporanAbsensiApp() {
    var showReport by remember { mutableStateOf(false) }

    if (showReport) {
        LaporanAbsensiDetailScreen(onBack = { showReport = false })
    } else {
        LaporanAbsensiScreen(onSubmit = { showReport = true })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaporanAbsensiScreen(onSubmit: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Laporan Absensi")
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back action */ }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LabelWithDropdown(
                label = "Fakultas",
                placeholder = "Pilih Fakultas",
                options = listOf("Teknologi Informasi", "Teknik")
            )
            Spacer(modifier = Modifier.height(16.dp))
            LabelWithDropdown(
                label = "Jurusan",
                placeholder = "Pilih Jurusan",
                options = listOf("Sistem Informasi", "Teknik Komputer")
            )
            Spacer(modifier = Modifier.height(16.dp))
            LabelWithDropdown(
                label = "Mata Kuliah",
                placeholder = "Pilih Mata Kuliah",
                options = listOf("PTB", "E-commerce")
            )
            Spacer(modifier = Modifier.height(16.dp))
            LabelWithKategoriDialog(onSubmit = onSubmit)
        }
    }
}

@Composable
fun LabelWithDropdown(label: String, placeholder: String, options: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(placeholder) }

    Column(horizontalAlignment = Alignment.Start) {
        Text(text = label, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color(0xFFEEEEEE))
        ) {
            OutlinedButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = selectedOption, fontSize = 16.sp)
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Dropdown Arrow",
                        tint = Color.Gray
                    )
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color(0xFFEEEEEE))
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOption = option
                            expanded = false
                        },
                        text = { Text(option) }
                    )
                }
            }
        }
    }
}

@Composable
fun LabelWithKategoriDialog(onSubmit: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedKategori by remember { mutableStateOf("") }
    val kategoriList = listOf("Reguler", "Praktikum", "Ujian")
    val selectedState = remember { mutableStateListOf(false, false, false) }

    Column(horizontalAlignment = Alignment.Start) {
        Text(text = "Kategori", fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
        OutlinedButton(
            onClick = { showDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (selectedKategori.isEmpty()) "Pilih Kategori" else selectedKategori,
                    fontSize = 16.sp
                )
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Dropdown Arrow",
                    tint = Color.Gray
                )
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Pilih Kategori") },
                text = {
                    Column {
                        kategoriList.forEachIndexed { index, text ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = selectedState[index],
                                    onCheckedChange = { selectedState[index] = it }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text, fontSize = 16.sp)
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Tutup")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                selectedKategori = kategoriList.filterIndexed { index, _ -> selectedState[index] }
                    .joinToString(", ")
                onSubmit()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("OK")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaporanAbsensiDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data Absensi") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                "Laporan Absensi Kelas",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Kelas: 2SIE01/Sistem Enterprise", fontWeight = FontWeight.SemiBold)
                    Text("Mata Kuliah: Perancangan Sistem Enterprise")
                    Text("SKS: 2")
                    Text("Kategori: Reguler")
                    Text("Periode: 01-01-2024 s/d 30-06-2024")
                    Text("Dosen: Nama Dosen")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(vertical = 8.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Pertemuan", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Hadir", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Alpha", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Sakit", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Izin", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            }

            val attendanceData = listOf(
                AttendanceData("Pertemuan 1", 25, 3, 2, 1),
                AttendanceData("Pertemuan 2", 22, 4, 1, 2),
                AttendanceData("Pertemuan 3", 28, 2, 3, 0),
                AttendanceData("Pertemuan 4", 26, 5, 1, 1),
                AttendanceData("Pertemuan 5", 30, 0, 2, 3),
                AttendanceData("Pertemuan 6", 27, 3, 0, 4)
            )

            val selectedMeetings = remember { mutableStateListOf(*Array(attendanceData.size) { false }) }

            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(attendanceData) { index, data ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedMeetings[index],
                            onCheckedChange = { checked ->
                                selectedMeetings[index] = checked
                            }
                        )
                        Text(data.pertemuan, modifier = Modifier.weight(1f))
                        Text(data.hadir.toString(), modifier = Modifier.weight(1f))
                        Text(data.alpha.toString(), modifier = Modifier.weight(1f))
                        Text(data.sakit.toString(), modifier = Modifier.weight(1f))
                        Text(data.izin.toString(), modifier = Modifier.weight(1f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val selectedIndices = selectedMeetings
                        .mapIndexedNotNull { index, isSelected -> if (isSelected) attendanceData[index].pertemuan else null }
                        .joinToString(", ")
                    println("Detail for: $selectedIndices")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Lihat Detail", color = Color.White)
            }
        }
    }
}

data class AttendanceData(
    val pertemuan: String,
    val hadir: Int,
    val alpha: Int,
    val sakit: Int,
    val izin: Int
)

@Preview(showBackground = true)
@Composable
fun PreviewLaporanAbsensiDetailScreen() {
    MaterialTheme {
        LaporanAbsensiDetailScreen(onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLaporanAbsensiApp() {
    MaterialTheme {
        LaporanAbsensiApp()
    }
}
