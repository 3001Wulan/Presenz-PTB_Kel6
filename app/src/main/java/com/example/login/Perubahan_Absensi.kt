package com.example.presenz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

class PerubahanAbsensiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttendanceScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen() {
    var selectedFaculty by remember { mutableStateOf("") }
    var selectedProgram by remember { mutableStateOf("") }
    var selectedCourse by remember { mutableStateOf("") }

    // Warna background untuk setiap card
    var facultyBackgroundColor by remember { mutableStateOf(Color(0xFFBABDB8)) } // Contoh warna
    var programBackgroundColor by remember { mutableStateOf(Color(0xFFBABDB8)) } // Contoh warna
    var courseBackgroundColor by remember { mutableStateOf(Color(0xFFBABDB8)) } // Contoh warna

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        // Header dengan tombol kembali, judul, dan kotak "Karina"
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD9D9D9), RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            // Row untuk tombol Kembali dan Judul
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tombol Kembali
                IconButton(
                    onClick = { /* Handle Back */ },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }

                // Judul
                Text(
                    text = "Perubahan Absensi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f) // Membuat teks perubahaan absensi berada di sebelah kanan
                )
            }

            // Kotak yang berisi "Karina" dan NIP
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(IntrinsicSize.Min) // Ukuran kotak disesuaikan dengan tinggi konten
                    .background(Color(0xFFE8EFF8), RoundedCornerShape(8.dp))
                    .padding(horizontal = 80.dp, vertical = 10.dp) // Menambah padding horizontal agar kotak lebih lebar
                    .align(Alignment.CenterHorizontally) // Menempatkan kotak di tengah secara horizontal
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, // Menempatkan teks di dalam kotak di tengah secara horizontal
                    verticalArrangement = Arrangement.Center // Menempatkan teks di tengah secara vertikal
                ) {
                    Text("Karina", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text("Nip.12334", fontSize = 14.sp, color = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Kartu untuk Fakultas, Program, dan Mata Kuliah
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = facultyBackgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Pilih Fakultas", fontSize = 16.sp, color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = programBackgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Pilih Jurusan", fontSize = 16.sp, color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = courseBackgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Pilih Mata Kuliah", fontSize = 16.sp, color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pilihan Pertemuan (Kartu)
        MeetingOptionCard("Pertemuan 1 kamis 12/01/24 (13.00-15.00)")
        Spacer(modifier = Modifier.height(8.dp))
        MeetingOptionCard("Pertemuan 2 kamis 19/01/24 (13.00-15.00)")
        Spacer(modifier = Modifier.height(8.dp))
        MeetingOptionCard("Pertemuan 3 kamis 25/01/24 (13.00-15.00)")
    }
}

@Composable
fun MeetingOptionCard(meetingInfo: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFBABDB8))
                .padding(12.dp), // Mengurangi padding untuk ukuran yang lebih pas
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(meetingInfo, fontSize = 12.sp, color = Color.Black) // Ukuran font lebih kecil
            Button(
                onClick = { /* Handle selection */ },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF73A0D7))
            ) {
                Text("Pilih", color = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAttendanceScreen() {
    AttendanceScreen()
}
