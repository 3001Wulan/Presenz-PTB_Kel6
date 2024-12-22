package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LaporanAbsensiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LaporanAbsensiScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaporanAbsensiScreen() {
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
            LabelWithDropdown(label = "Fakultas", placeholder = "Pilih Fakultas")
            Spacer(modifier = Modifier.height(16.dp))
            LabelWithDropdown(label = "Jurusan", placeholder = "Pilih Jurusan")
            Spacer(modifier = Modifier.height(16.dp))
            LabelWithDropdown(label = "Mata Kuliah", placeholder = "Pilih Mata Kuliah")
            Spacer(modifier = Modifier.height(16.dp))
            LabelWithDropdown(label = "Kategori", placeholder = "Pilih Kategori")
        }
    }
}

@Composable
fun LabelWithDropdown(label: String, placeholder: String) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(text = label, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
        DropdownField(placeholder = placeholder)
    }
}

@Composable
fun DropdownField(placeholder: String) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(placeholder) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color(0xFFEEEEEE)) // Warna abu-abu untuk kotak dropdown
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
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Dropdown Arrow",
                    tint = Color.Gray // Warna panah
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFFEEEEEE)) // Warna abu-abu untuk menu dropdown
        ) {
            val options = listOf("Opsi 1", "Opsi 2", "Opsi 3")
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

@Preview(showBackground = true)
@Composable
fun PreviewLaporanAbsensiScreen() {
    MaterialTheme {
        LaporanAbsensiScreen()
    }
}
