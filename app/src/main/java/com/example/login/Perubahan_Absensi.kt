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
    var expandedFaculty by remember { mutableStateOf(false) }
    var expandedProgram by remember { mutableStateOf(false) }
    var expandedCourse by remember { mutableStateOf(false) }

    val faculties = listOf("Fakultas Teknologi Informasi", "Fakultas Teknik")
    val programs = listOf("Sistem Informasi", "Teknik Komputer", "Informatika")
    val courses = listOf("Pemrograman Teknologi Bergerak", "E-Commerce")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD9D9D9), RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { /* Handle Back */ },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }

                Text(
                    text = "Perubahan Absensi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
            }

            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(IntrinsicSize.Min)
                    .background(Color(0xFFE8EFF8), RoundedCornerShape(8.dp))
                    .padding(horizontal = 80.dp, vertical = 10.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Karina", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text("Nip.12334", fontSize = 14.sp, color = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Fakultas Dropdown
        ExposedDropdownMenuBox(
            expanded = expandedFaculty,
            onExpandedChange = { expandedFaculty = it },
        ) {
            OutlinedTextField(
                value = selectedFaculty,
                onValueChange = { selectedFaculty = it },
                label = { Text("Fakultas") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedFaculty)
                },
                readOnly = true,
                modifier = Modifier.fillMaxWidth().menuAnchor(),
            )
            ExposedDropdownMenu(
                expanded = expandedFaculty,
                onDismissRequest = { expandedFaculty = false }
            ) {
                faculties.forEach { faculty ->
                    DropdownMenuItem(
                        text = { Text(faculty) },
                        onClick = {
                            selectedFaculty = faculty
                            expandedFaculty = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Program Dropdown
        ExposedDropdownMenuBox(
            expanded = expandedProgram,
            onExpandedChange = { expandedProgram = it },
        ) {
            OutlinedTextField(
                value = selectedProgram,
                onValueChange = { selectedProgram = it },
                label = { Text("Jurusan") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedProgram)
                },
                readOnly = true,
                modifier = Modifier.fillMaxWidth().menuAnchor(),
            )
            ExposedDropdownMenu(
                expanded = expandedProgram,
                onDismissRequest = { expandedProgram = false }
            ) {
                programs.forEach { program ->
                    DropdownMenuItem(
                        text = { Text(program) },
                        onClick = {
                            selectedProgram = program
                            expandedProgram = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Mata Kuliah Dropdown
        ExposedDropdownMenuBox(
            expanded = expandedCourse,
            onExpandedChange = { expandedCourse = it },
        ) {
            OutlinedTextField(
                value = selectedCourse,
                onValueChange = { selectedCourse = it },
                label = { Text("Mata Kuliah") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCourse)
                },
                readOnly = true,
                modifier = Modifier.fillMaxWidth().menuAnchor(),
            )
            ExposedDropdownMenu(
                expanded = expandedCourse,
                onDismissRequest = { expandedCourse = false }
            ) {
                courses.forEach { course ->
                    DropdownMenuItem(
                        text = { Text(course) },
                        onClick = {
                            selectedCourse = course
                            expandedCourse = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Conditional Meeting Option Cards
        if (selectedCourse.isNotEmpty()) {
            MeetingOptionCard("Pertemuan 1 Kamis 12/01/24 (13.00-15.00)")
            Spacer(modifier = Modifier.height(8.dp))
            MeetingOptionCard("Pertemuan 2 Kamis 19/01/24 (13.00-15.00)")
            Spacer(modifier = Modifier.height(8.dp))
            MeetingOptionCard("Pertemuan 3 Kamis 25/01/24 (13.00-15.00)")
        }
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
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(meetingInfo, fontSize = 12.sp, color = Color.Black)
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
