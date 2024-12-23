package com.example.presenz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R

class RekapPertemuan : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RekapPertemuanUI()
        }
    }
}

@Composable
fun RekapPertemuanUI() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header dengan warna abu-abu muda
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD9D9D9)) // Warna abu-abu muda
                .padding(16.dp)
        ) {
            HeaderWithBackButton()
        }

        // Konten utama
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Label "Mata Kuliah"
            Text(
                text = "Mata Kuliah",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Dropdown untuk memilih mata kuliah
            MataKuliahDropdown()

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol OK dengan warna custom
            Button(
                onClick = { /* Lakukan aksi */ },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF73A0D7), // Ganti dengan warna latar belakang tombol
                    contentColor = Color.Black // Ganti dengan warna teks tombol
                )
            ) {
                Text(
                    text = "OK",
                    style = TextStyle(
                        fontSize = 20.sp,   // Ukuran font lebih besar
                        fontWeight = FontWeight.Bold // Teks tebal
                    )
                )
            }
        }
    }
}

@Composable
fun HeaderWithBackButton() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Tombol back dengan ikon bawaan
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { /* Tambahkan aksi back */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, // Ikon back bawaan
                    contentDescription = "Kembali"
                )
            }

            // Teks judul
            Text(
                text = "Rekap Pertemuan",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(start = 8.dp) // Jarak teks dari ikon
            )
        }

        // Profil di bawah tombol back
        Spacer(modifier = Modifier.height(16.dp)) // Memberi jarak antara teks dan profil
        ProfileSection() // Menampilkan profil di bawah tombol back
    }
}

@Composable
fun ProfileSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(start = 45.dp) // Memberikan sedikit jarak ke kanan untuk geser profil
    ) {
        // Gambar Profil
        Image(
            painter = painterResource(id = R.drawable.profil2), // Ganti dengan gambar Anda
            contentDescription = "Gambar Profil",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Nama Profil
        Column {
            Text(
                text = "Ratna",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
            )
            Text(
                text = "LEA",
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

@Composable
fun MataKuliahDropdown() {
    var expanded by remember { mutableStateOf(false) }
    var selectedMataKuliah by remember { mutableStateOf("Pilih Mata Kuliah") }

    // Box dengan sudut lebih melengkung
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium) // Membuat sudut lebih melengkung
            .background(Color(0xFFBABDB8)) // Warna background box
            .clickable { expanded = true }
            .padding(horizontal = 16.dp, vertical = 12.dp) // Padding internal untuk teks
    ) {
        Text(
            text = selectedMataKuliah,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Data Mining") },
                onClick = {
                    selectedMataKuliah = "Data Mining"
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("E-Bussiness") },
                onClick = {
                    selectedMataKuliah = "E-Bussiness"
                    expanded = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RekapPertemuanPreview() {
    RekapPertemuanUI()
}