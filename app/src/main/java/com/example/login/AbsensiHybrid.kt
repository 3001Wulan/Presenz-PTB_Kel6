@file:Suppress("DEPRECATION")

package com.example.absensiapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.LaptopMac
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier

@Composable
fun AbsensiScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8EFF8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Menambahkan isOnline = false untuk menampilkan "Opsi Absensi" di header
        HeaderWithBackIcon(navController, isOnline = false)

        Spacer(modifier = Modifier.height(16.dp))

        PilihJenisAbsensiOval()

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KehadiranTableWithIcon(
                title = "Kehadiran di Kelas",
                buttonText = "Pilih",
                onButtonClick = { navController.navigate("absensi_offline") },
                icon = Icons.Filled.Domain,
                backgroundColor = Color(0xFFD9D9D9)
            )

            KehadiranTableWithIcon(
                title = "Kehadiran Secara Online",
                buttonText = "Pilih",
                onButtonClick = { navController.navigate("absensi_online") },
                icon = Icons.Filled.LaptopMac,
                backgroundColor = Color(0xFFD9D9D9)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        PilihJenisKehadiranOval()
    }
}

@Composable
fun HeaderWithBackIcon(navController: NavController, isOnline: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Kembali",
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Menampilkan teks sesuai halaman (Opsi Absensi untuk halaman utama, In-Class untuk offline, Sub-Class untuk online)
        Text(
            text = when {
                isOnline && navController.currentDestination?.route == "absensi_online" -> "Sub Class"  // Online
                !isOnline && navController.currentDestination?.route == "absensi_offline" -> "In-Class"  // Offline
                else -> "Opsi Absensi"  // Untuk halaman utama
            },
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun PilihJenisAbsensiOval() {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(50.dp)),
        elevation = 4.dp,
        backgroundColor = Color.LightGray
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Pilih Jenis Absensi",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun PilihJenisKehadiranOval() {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(50.dp)),
        elevation = 4.dp,
        backgroundColor = Color.LightGray
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Pilih Jenis Kehadiran",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun KehadiranTableWithIcon(
    title: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    icon: ImageVector,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier
            .width(140.dp)  // Mengurangi lebar Card dari 160dp menjadi 140dp
            .padding(8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onButtonClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF73A0D7))
            ) {
                Text(text = buttonText)
            }
        }
    }
}


// Absensi Offline Screen
@Composable
fun AbsensiOfflineScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFE8EFF8))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                HeaderWithBackIcon(navController, isOnline = false)

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Student Attendance",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(16.dp))
                PilihPertemuanTable()
                Spacer(modifier = Modifier.height(16.dp))
                StudentAttendanceList()
                Spacer(modifier = Modifier.height(32.dp))

                // Tombol Save
                Button(
                    onClick = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Berhasil Save!")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(48.dp)
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF73A0D7)),
                    shape = RoundedCornerShape(4.dp)  // Ganti 16.dp menjadi 4.dp untuk sudut yang lebih tajam
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@Composable
fun AbsensiOnlineScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFE8EFF8))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                HeaderWithBackIcon(navController, isOnline = true)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Student Attendance",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(16.dp))
                PilihPertemuanTable()
                Spacer(modifier = Modifier.height(16.dp))
                StudentAttendanceList()
                Spacer(modifier = Modifier.height(32.dp))

                // Tombol Save
                Button(
                    onClick = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Berhasil Save!")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(48.dp)
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF73A0D7)),
                    shape = RoundedCornerShape(4.dp)  // Ganti 16.dp menjadi 4.dp untuk sudut yang lebih tajam
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}

// Bagian lainnya seperti PilihJenisAbsensiOval(), KehadiranTableWithIcon(), dll.


// Bagian lainnya seperti PilihJenisAbsensiOval(), KehadiranTableWithIcon(), StudentAttendanceList(), dll.
// Harus tetap dipertahankan seperti pada kode sebelumnya.
@Composable
fun StudentAttendanceList() {
    val students = listOf(
        "Natasha Wilona",
        "Selena Gomez",
        "Zayn Malik",
        "Bruno Mars",
        "Arbani Yasiz",
        "Jisoo"
    )

    // Status kehadiran untuk masing-masing siswa
    val attendanceStatus = remember { mutableStateOf(List(students.size) { "Hadir" }) }

    Column(modifier = Modifier.fillMaxWidth()) {
        students.forEachIndexed { index, student ->
            // Menggunakan remember untuk menyimpan status dropdown setiap siswa
            var expanded by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = 2.dp,
                backgroundColor = Color(0xFFD9D9D9),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Kolom untuk Nama Siswa dan ID
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = student,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.body2,
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "221152100${index + 1}", // ID siswa dinamis
                            style = MaterialTheme.typography.caption,
                            color = Color.Gray
                        )
                    }

                    // Status Kehadiran dengan Dropdown Menu
                    Box {
                        Card(
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(RoundedCornerShape(16.dp)),
                            backgroundColor = Color.White,
                            elevation = 2.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                    .clickable { expanded = true }, // Klik untuk membuka menu
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = attendanceStatus.value[index],
                                    fontWeight = FontWeight.Medium,
                                    style = MaterialTheme.typography.body2,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = "Dropdown",
                                    tint = Color.Black
                                )
                            }
                        }

                        // Dropdown menu
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            listOf("Hadir", "Sakit", "Izin", "Alfa").forEach { status ->
                                DropdownMenuItem(onClick = {
                                    attendanceStatus.value = attendanceStatus.value.toMutableList().apply {
                                        this[index] = status
                                    }
                                    expanded = false
                                }) {
                                    Text(text = status)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PilihPertemuanTable() {
    // Daftar Pertemuan hanya hingga Pertemuan 8
    val meetings = (1..8).map { "Pertemuan $it" }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Label di luar tabel
        Text(
            text = "Pertemuan",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Tabel Pilih Pertemuan
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f) // Memperpanjang tabel lebih lebar
                .height(40.dp), // Membuat tabel lebih pendek
            shape = RoundedCornerShape(16.dp), // Membulatkan garis tepi lebih halus
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth()
            ) {
                var expanded by remember { mutableStateOf(false) }
                var selectedMeeting by remember { mutableStateOf("") } // Awalnya kosong

                Box {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (selectedMeeting.isEmpty()) "Pilih Pertemuan" else selectedMeeting,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.body2,
                            color = Color.Black,
                            modifier = Modifier.weight(1f) // Mengambil ruang sisa untuk teks
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Dropdown",
                            tint = Color.Black
                        )
                    }

                    // Dropdown menu untuk memilih pertemuan
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .background(Color.White)
                            .offset(y = 0.dp) // Memastikan dropdown tetap muncul ke bawah
                    ) {
                        meetings.forEach { meeting ->
                            DropdownMenuItem(onClick = {
                                selectedMeeting = meeting // Mengatur teks setelah dipilih
                                expanded = false
                            }) {
                                Text(text = meeting)
                            }
                        }
                    }
                }
            }
        }
    }
}





// Navigation setup
@Composable
fun AbsensiApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "absensi_screen") {
        composable("absensi_screen") {
            AbsensiScreen(navController)
        }
        composable("absensi_offline") {
            AbsensiOfflineScreen(navController)
        }
        composable("absensi_online") {
            AbsensiOnlineScreen(navController) // Halaman untuk Kehadiran Online
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AbsensiAppPreview() {
    val navController = rememberNavController()

    // Sistem navigasi lengkap untuk halaman preview
    NavHost(navController = navController, startDestination = "absensi_screen") {
        composable("absensi_screen") {
            AbsensiScreen(navController = navController)
        }
        composable("absensi_offline") {
            AbsensiOfflineScreen(navController = navController)
        }
        composable("absensi_online") {
            AbsensiOnlineScreen(navController = navController)
        }
    }
}



