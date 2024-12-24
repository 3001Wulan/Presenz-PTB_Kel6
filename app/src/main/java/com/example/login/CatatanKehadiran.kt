package com.example.attendance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.login.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttendanceScreen()
        }
    }
}

@Composable
fun AttendanceScreen() {
    var selectedSubject by remember { mutableStateOf("Pilih Mata Kuliah") }
    var selectedMeeting by remember { mutableStateOf("Pilih Pertemuan") }
    var attendanceNote by remember { mutableStateOf("") }
    var summaryNote by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F9FF))
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Arrow",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Catatan Kehadiran",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Gambar foto profil diambil dari drawable
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    // Memuat gambar dari folder drawable menggunakan painterResource
                    Image(
                        painter = painterResource(id = R.drawable.profil2), // Ganti dengan nama file gambar di drawable
                        contentDescription = "Foto Profil",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Text("Karina", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("NIP: 123334", fontSize = 16.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            DropdownField(
                label = "Mata Kuliah",
                options = listOf("Pemrograman Teknologi Bergerak", "E-Bisnis", "Akuisisi Data"),
                selectedValue = selectedSubject,
                onValueChange = { selectedSubject = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            DropdownField(
                label = "Pertemuan",
                options = listOf("Pertemuan 1", "Pertemuan 2", "Pertemuan 3"),
                selectedValue = selectedMeeting,
                onValueChange = { selectedMeeting = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /* Logic for OK button */ },
                    modifier = Modifier
                        .height(48.dp)
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF73A0D7))
                ) {
                    Text("OK", color = Color.Black, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFACACAC)) // Warna tabel diperbarui
                    .padding(16.dp)
            ) {
                InputField(
                    label = "Catatan Kehadiran",
                    value = attendanceNote,
                    onValueChange = { attendanceNote = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFACACAC)) // Warna tabel diperbarui
                    .padding(16.dp)
            ) {
                InputField(
                    label = "Kesimpulan Pertemuan",
                    value = summaryNote,
                    onValueChange = { summaryNote = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Logic to save or preview the data */ },
                modifier = Modifier
                    .width(150.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF73A0D7))
            ) {
                Text("Save", color = Color.Black, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    selectedValue: String,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(text = label, fontSize = 14.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFACACAC))
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = selectedValue, fontSize = 16.sp)
                    IconButton(
                        onClick = { expanded = !expanded },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Dropdown Arrow",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onValueChange(option)
                    expanded = false
                }) {
                    Text(option)
                }
            }
        }
    }
}

@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 14.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(8.dp),
            textStyle = TextStyle(fontSize = 16.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAttendanceScreen() {
    AttendanceScreen()
}
