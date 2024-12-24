package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.login.R // Ganti dengan package proyek Anda

class NotifikasiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationScreen()
        }
    }
}

@Composable
fun NotificationScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Gambar latar belakang
        Image(
            painter = painterResource(id = R.drawable.background_books), // Ganti dengan resource gambar latar belakang Anda
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Konten notifikasi
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(bottom = 56.dp), // Tambahkan padding agar tidak tertutup BottomAppBar
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header dengan ikon back
            HeaderWithBackButton()

            // Kartu notifikasi
            NotificationCard(
                title = "Jangan lupa ada kelas hari ini!",
                details = "Kelas E-Commerce B, pada pukul 13.30 - 16.00 WIB\ndi Gedung H, ruangan H 2.10"
            )
            NotificationCard(
                title = "Halo, Besok ada kelas ya!",
                details = "Kelas E-Business A, pada pukul 09.20 - 11.50 WIB\ndi Gedung H, ruangan H 1.9"
            )
            NotificationCard(
                title = "Jangan lupa ada kelas hari ini!",
                details = "Kelas E-Business A, pada pukul 09.20 - 11.50 WIB\ndi Gedung H, ruangan H 1.9"
            )
        }

        // Navigasi bawah
        BottomNavigationBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun HeaderWithBackButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* TODO: Handle back action */ }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }
        Text(
            text = "Notification",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp) // Memberikan jarak antara ikon dan teks
        )
    }
}

@Composable
fun NotificationCard(title: String, details: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.9f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = details,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        contentPadding = PaddingValues(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { /* TODO: Handle navigation */ }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = Color.Black
                )
            }
            IconButton(onClick = { /* TODO: Handle navigation */ }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile",
                    tint = Color.Black
                )
            }
            IconButton(onClick = { /* TODO: Handle navigation */ }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen()
}
