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

class AbsensiUjian : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AbsensiUjianUI()
        }
    }
}

@Composable
fun AbsensiUjianUI() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header dengan warna abu-abu muda
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD9D9D9)) // Warna abu-abu muda
                .padding(16.dp)
        ) {
            HeaderWithBackButtonAbsensi() // Menggunakan nama fungsi yang telah diganti
        }

        // Konten utama
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Label dan Dropdown untuk Fakultas
            Text(
                "Fakultas",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp) // Tambah jarak bawah
            )
            DropdownSection(options = listOf("Pilih Fakultas", "Fakultas Teknik", "Fakultas Ekonomi"))
            Spacer(modifier = Modifier.height(16.dp)) // Tambah jarak antar dropdown

            // Label dan Dropdown untuk Prodi
            Text(
                "Prodi",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp) // Tambah jarak bawah
            )
            DropdownSection(options = listOf("Pilih Jurusan", "Teknik Informatika", "Manajemen"))
            Spacer(modifier = Modifier.height(16.dp)) // Tambah jarak antar dropdown

            // Tombol OK setelah dropdown Prodi
            Spacer(modifier = Modifier.height(16.dp)) // Memberikan jarak antara dropdown dan tombol OK
            Button(
                onClick = { /* Lakukan aksi OK */ },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF73A0D7), // Warna tombol OK
                    contentColor = Color.Black // Warna teks tombol
                )
            ) {
                Text(
                    text = "OK",
                    style = TextStyle(
                        fontSize = 15.sp,   // Ukuran font lebih besar
                        fontWeight = FontWeight.Bold // Teks tebal
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Label dan Dropdown untuk Mata Kuliah
            Text(
                "Mata Kuliah",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp) // Tambah jarak bawah
            )
            DropdownSection(options = listOf("Pilih Mata Kuliah", "Pemrograman", "Sistem Operasi"))
            Spacer(modifier = Modifier.height(16.dp)) // Tambah jarak antar dropdown

            // Label dan Dropdown untuk Semester
            Text(
                "Semester",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp) // Tambah jarak bawah
            )
            DropdownSection(options = listOf("Pilih Semester", "Semester 1", "Semester 2"))
            Spacer(modifier = Modifier.height(16.dp)) // Tambah jarak antar dropdown

            // Label dan Dropdown untuk Waktu
            Text(
                "Waktu",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp) // Tambah jarak bawah
            )
            DropdownSection(options = listOf("Pilih Waktu", "Pagi", "Siang"))

            // Tombol Edit dan Simpan di bawah semua dropdown
            Spacer(modifier = Modifier.height(16.dp)) // Memberikan jarak
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Tombol Edit
                Button(
                    onClick = { /* Lakukan aksi Edit */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF73A0D7), // Warna tombol Edit sama dengan tombol OK
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Edit",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                // Tombol Simpan
                Button(
                    onClick = { /* Lakukan aksi Simpan */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF73A0D7), // Warna tombol Simpan sama dengan tombol OK
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Simpan",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderWithBackButtonAbsensi() { // Nama fungsi telah diganti
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
                text = "Absensi Ujian",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(start = 8.dp) // Jarak teks dari ikon
            )
        }

        // Profil di bawah tombol back
        Spacer(modifier = Modifier.height(16.dp)) // Memberi jarak antara teks dan profil
        ProfilSection() // Menampilkan profil di bawah tombol back
    }
}

@Composable
fun ProfilSection() {
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
                text = "Raisya",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
            )
            Text(
                text = "233444",
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

@Composable
fun DropdownSection(options: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options.first()) }

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
            text = selectedOption,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AbsensiUjianPreview() {
    AbsensiUjianUI()
}
