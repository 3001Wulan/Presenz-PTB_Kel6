package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login.ui.theme.NotesTheme

class CatatanPertemuanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                CatatanPertemuanScreen()
            }
        }
    }
}

@Composable
fun CatatanPertemuanScreen() {
    var selectedMataKuliah by remember { mutableStateOf("") }
    var selectedPertemuan by remember { mutableStateOf("") }
    var tema by remember { mutableStateOf("") }
    var topikPertemuan by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Gabungkan Header dan Profil
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Pastikan mengambil seluruh lebar layar
                    .background(Color(0xFFE0E0E0)) // Warna latar belakang menyatu
                    .padding(horizontal = 16.dp, vertical = 16.dp) // Padding untuk konten di dalamnya
            ) {
                Column(modifier = Modifier.fillMaxWidth()) { // Pastikan kolom di dalamnya juga lebar penuh
                    // Header
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Catatan Pertemuan",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Profil Diri
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth() // Tambahkan lebar penuh
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profil2),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Karina",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = "NIP.123334",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown Pilihan Mata Kuliah
            DropdownField(
                label = "Pilih Mata Kuliah",
                value = selectedMataKuliah,
                onValueChange = { selectedMataKuliah = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown Pilihan Pertemuan
            DropdownField(
                label = "Pilih Pertemuan",
                value = selectedPertemuan,
                onValueChange = { selectedPertemuan = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input Tema
            OutlinedTextField(
                value = tema,
                onValueChange = { tema = it },
                label = { Text("Tema") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input Topik Pertemuan
            OutlinedTextField(
                value = topikPertemuan,
                onValueChange = { topikPertemuan = it },
                label = { Text("Topik Pertemuan") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Button Save
            Button(
                onClick = { /* Save logic */ },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 16.dp)
            ) {
                Text("Save")
            }
        }
    }
}



@Composable
fun DropdownField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = value,
            onValueChange = { },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOf("Pilihan 1", "Pilihan 2", "Pilihan 3").forEach { selection ->
                DropdownMenuItem(
                    text = { Text(selection) },
                    onClick = {
                        onValueChange(selection)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatatanPertemuanPreview() {
    NotesTheme {
        CatatanPertemuanScreen()
    }
}
