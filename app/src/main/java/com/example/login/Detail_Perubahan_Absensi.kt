package com.example.presenz.ui.theme

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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presenz.R

class PerubahanAbsensi : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PerubahanAbsensiScreen()
        }
    }
}

@Composable
fun PerubahanAbsensiScreen() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE8EFF8))
                .padding(16.dp)
        ) {
            // Header Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back Button
                IconButton(
                    onClick = { /* Handle Back Action */ },
                    modifier = Modifier
                        .size(35.dp)
                        .padding(end = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                // Title
                Text(
                    text = "Perubahan Absensi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Box with Project Info
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD9D9D9), RoundedCornerShape(30.dp))
                    .padding(vertical = 15.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "E-Commerce",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Schedule Box
            Box(
                modifier = Modifier
            ) {
                Text(
                    text = "Pertemuan 1\nRabu, 21/08/2024, 13.30-16.00 WIB",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Attendees List
            val attendees = listOf(
                Triple("Natasha Wilona", "2211521001", R.drawable.natasha),
                Triple("Selena Gomez", "2211521001", R.drawable.selena),
                Triple("Zayn Malik", "2211521001", R.drawable.zayn),
                Triple("Bruno Mars", "2211521001", R.drawable.bruno),
                Triple("Arbani Yasiz", "2211521001", R.drawable.arbani),
                Triple("Jisoo", "2211521001", R.drawable.jisoo)
            )

            attendees.forEach { attendee ->
                PerubahanAbsensiRow(name = attendee.first, id = attendee.second, imageRes = attendee.third)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Save Button
            Button(
                onClick = { /* Handle Save */ },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF73A0D7))
            ) {
                Text(text = "Update", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerubahanAbsensiRow(name: String, id: String, imageRes: Int) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Hadir") }
    val options = listOf("Hadir", "Tidak Hadir", "Izin", "Sakit", "Alpha")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD9D9D9), RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "$name's Avatar",
            modifier = Modifier
                .size(40.dp)
                .background(Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = id, fontSize = 12.sp, color = Color.Black)
        }

        // Exposed Dropdown Menu for Presence Options
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                label = null,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE8EFF8),
                    disabledTrailingIconColor = Color.Gray
                ),
                modifier = Modifier
                    .width(120.dp)
                    .height(36.dp)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOption = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerubahanAbsensiPreview() {
    PerubahanAbsensiScreen()
}
