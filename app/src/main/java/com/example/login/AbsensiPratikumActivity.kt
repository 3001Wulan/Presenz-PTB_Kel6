package com.example.AbsensiPraktikum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R

class AbsensiPraktikumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AbsensiPraktikumContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbsensiPraktikumContent() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Absensi Praktikum", fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle Back Action */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFD9D9D9)
                )
            )
        },
        content = { paddingValues ->
            AbsensiPraktikumBody(Modifier.padding(paddingValues))
        }
    )
}

@Composable
fun AbsensiPraktikumBody(modifier: Modifier = Modifier) {
    var selectedFakultas by remember { mutableStateOf("") }
    var selectedProdi by remember { mutableStateOf("") }
    var selectedMataKuliah by remember { mutableStateOf("") }
    var selectedPertemuan by remember { mutableStateOf("") }
    var selectedWaktu by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4FF))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD9D9D9))
                .padding(vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.Gray, CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profil),
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        "Ratna",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    Text(
                        "LEA",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Fakultas",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.Black
        )
        DropdownField("Pilih Fakultas", selectedFakultas) { selectedFakultas = it }

        Text(
            text = "Prodi",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.Black
        )
        DropdownField("Pilih Jurusan", selectedProdi) { selectedProdi = it }

        Text(
            text = "Mata Kuliah",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.Black
        )
        DropdownField("Pilih Mata Kuliah", selectedMataKuliah) { selectedMataKuliah = it }

        Text(
            text = "Pertemuan",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.Black
        )
        DropdownField("Pilih Pertemuan", selectedPertemuan) { selectedPertemuan = it }

        Text(
            text = "Waktu",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.Black
        )
        DropdownField("Pilih Waktu", selectedWaktu) { selectedWaktu = it }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ActionButton("Edit") { /* Handle Edit */ }
            ActionButton("Hapus") { /* Handle Hapus */ }
            ActionButton("Simpan") { /* Handle Simpan */ }
        }
    }
}

@Composable
fun DropdownField(label: String, selectedValue: String, onValueChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (selectedValue.isEmpty()) label else selectedValue,
                    textAlign = TextAlign.Start,
                    color = if (selectedValue.isEmpty()) Color.Gray else Color.Black
                )
                Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOf("Option 1", "Option 2", "Option 3").forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun ActionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color(0xFF8DA9FF)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAbsensiPraktikumContent() {
    AbsensiPraktikumContent()
}
