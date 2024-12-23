package com.example.presenz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.font.FontWeight
import com.example.login.R

class ArsipAbsensi : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArsipAbsensiScreen()
        }
    }
}

@Composable
fun ArsipAbsensiScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Header
        HeaderSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Pencarian
        SearchField()

        Spacer(modifier = Modifier.height(16.dp))

        // Daftar Arsip Absensi
        AbsensiList()
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Padding agar ada jarak dengan batas layar
    ) {
        // Tombol Back di sudut kiri atas
        IconButton(
            onClick = { /* Tindakan ketika tombol back ditekan */ },
            modifier = Modifier
                .align(Alignment.TopStart) // Menempatkan tombol di sudut kiri atas
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black // Warna ikon back
            )
        }

        // Teks header yang berada di tengah atas
        Text(
            text = "Penyimpanan Arsip Absensi",
            style = TextStyle(fontSize = 20.sp),
            color = Color.Black, // Warna teks tetap hitam
            modifier = Modifier.align(Alignment.Center) // Memastikan teks berada di tengah
        )
    }
}

@Composable
fun SearchField() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = { Text("Pencarian") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Color(0xFF73A0D7),
            unfocusedIndicatorColor = Color.Gray,
            focusedLabelColor = Color(0xFF73A0D7),
            unfocusedLabelColor = Color.Gray
        ),
        shape = MaterialTheme.shapes.small
    )
}

@Composable
fun AbsensiList() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Menambahkan judul Kategori dan tombol Lihat Semua
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Kategori",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )

            TextButton(onClick = { /* Tindakan untuk lihat semua */ }) {
                Text(text = "Lihat Semua", style = TextStyle(color = Color(0xFF73A0D7)))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Daftar Arsip Absensi
        AbsensiItem(name = "Karina", category = "Regular 2024/2025")
        Spacer(modifier = Modifier.height(16.dp))
        AbsensiItem(name = "Karina", category = "Praktikum 2023/2024")
        Spacer(modifier = Modifier.height(16.dp))
        AbsensiItem(name = "Karina", category = "Ujian Pemrograman Teknologi Bergerak 2022/2023")
    }
}

@Composable
fun AbsensiItem(name: String, category: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        // Mengganti warna background Card
        backgroundColor = Color(0xFFD9D9D9) // Ganti dengan warna yang diinginkan
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Gambar Profil
            Image(
                painter = painterResource(id = R.drawable.profil),
                contentDescription = "Gambar Profil",
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Nama dan kategori
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = TextStyle(fontSize = 16.sp, color = Color.Black)
                )
                Text(
                    text = category,
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                )
            }

            // Tombol Lihat dan Arsip
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { /* Tindakan untuk lihat */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF73A0D7))
                ) {
                    Text(text = "Lihat", color = Color.Black)
                }
                Button(
                    onClick = { /* Tindakan untuk arsipkan */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF73A0D7))
                ) {
                    Text(text = "Arsip", color = Color.Black)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArsipAbsensiPreview() {
    ArsipAbsensiScreen()
}
