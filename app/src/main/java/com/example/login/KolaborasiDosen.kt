package com.example.guruscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

data class Guru(val nama: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaftarGuruScreen()
        }
    }
}

@Composable
fun DaftarGuruScreen() {
    val listGuru = listOf(
        Guru("Karina M, Kom"),
        Guru("Giselle M, Kom"),
        Guru("Winter M, Kom"),
        Guru("Jungwon M, Kom")
    )

    val (selectedGuru, setSelectedGuru) = remember { mutableStateOf(setOf<Guru>()) }

    // Function to select all teachers
    fun selectAllTeachers() {
        setSelectedGuru(listGuru.toSet())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8EFF8))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { println("Back button pressed") }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Text(
                text = "Teacher List",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(listGuru) { guru ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFACACAC))
                        .padding(16.dp)
                ) {
                    // Kotak profil berbentuk lingkaran tanpa gambar
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.Gray) // Placeholder warna kotak profil
                            .align(Alignment.CenterVertically)
                    ) {
                        // Menampilkan inisial profil dosen
                        Text(
                            text = guru.nama.take(1), // Mengambil huruf pertama dari nama dosen sebagai inisial
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Nama dosen di sebelah kotak profil
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = guru.nama,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF73A0D7))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Syncron",
                                color = Color.Black,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Checkbox(
                        checked = selectedGuru.contains(guru),
                        onCheckedChange = {
                            if (it) setSelectedGuru(selectedGuru + guru)
                            else setSelectedGuru(selectedGuru - guru)
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF73A0D7),
                            uncheckedColor = Color.Gray
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    selectAllTeachers()
                    println("All teachers selected: $listGuru")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF73A0D7))
            ) {
                Text(text = "Synchron", color = Color.Black)
            }
            Button(
                onClick = {
                    setSelectedGuru(setOf())
                    println("Pemilihan dibatalkan")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF73A0D7))
            ) {
                Text(text = "Cancel", color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DaftarGuruScreen()
}
