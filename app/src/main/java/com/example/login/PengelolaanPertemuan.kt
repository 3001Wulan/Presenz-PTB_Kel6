import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreateMeetingScreen() {
    var selectedFaculty by remember { mutableStateOf("") }
    var selectedProgram by remember { mutableStateOf("") }
    var selectedSubject by remember { mutableStateOf("") }
    var selectedMeeting by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var selectedMeetingType by remember { mutableStateOf("") }
    var gpsEnabled by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf("") }

    // Tambahkan state untuk menyimpan dan menghapus status
    var isSaved by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()

    // Menampilkan Snackbar saat data disimpan
    LaunchedEffect(isSaved) {
        if (isSaved) {
            scaffoldState.snackbarHostState.showSnackbar("Berhasil Disimpan!")
            // Reset status setelah snackbar ditampilkan
            isSaved = false
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Membuat Pertemuan",
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                backgroundColor = Color.LightGray,
                navigationIcon = {
                    IconButton(onClick = { /* aksi kembali */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE8EFF8))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DropdownField(
                    label = "Pilih Fakultas",
                    selectedValue = selectedFaculty,
                    onValueChange = { selectedFaculty = it }
                )
                DropdownField(
                    label = "Pilih Jurusan",
                    selectedValue = selectedProgram,
                    onValueChange = { selectedProgram = it }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFACACAC), RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "GPS", fontSize = 16.sp, color = Color.Black)
                        Switch(
                            checked = gpsEnabled,
                            onCheckedChange = { gpsEnabled = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.Black,
                                uncheckedThumbColor = Color.LightGray,
                                checkedTrackColor = Color.Green,
                                uncheckedTrackColor = Color.Gray
                            )
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFACACAC), RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    TextField(
                        value = location,
                        onValueChange = { location = it },
                        placeholder = { Text("Lokasi", color = Color.Black) },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Black,
                            backgroundColor = Color.Transparent,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                DropdownField(
                    label = "Pilih Mata Kuliah",
                    selectedValue = selectedSubject,
                    onValueChange = { selectedSubject = it }
                )
                DropdownField(
                    label = "Pilih Pertemuan",
                    selectedValue = selectedMeeting,
                    onValueChange = { selectedMeeting = it }
                )
                DropdownField(
                    label = "Pilih Waktu",
                    selectedValue = selectedTime,
                    onValueChange = { selectedTime = it }
                )
                DropdownField(
                    label = "Pilih Jenis Pertemuan",
                    selectedValue = selectedMeetingType,
                    onValueChange = { selectedMeetingType = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            // Logika untuk mengedit pertemuan
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF73A0D7)),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Text(text = "Edit", color = Color.Black)
                    }
                    Button(
                        onClick = {
                            // Reset nilai-nilai input
                            selectedFaculty = ""
                            selectedProgram = ""
                            selectedSubject = ""
                            selectedMeeting = ""
                            selectedTime = ""
                            selectedMeetingType = ""
                            gpsEnabled = false
                            location = ""
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF73A0D7)),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(text = "Hapus", color = Color.Black)
                    }
                    Button(
                        onClick = {
                            isSaved = true
                            // Menampilkan pesan berhasil disimpan
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF73A0D7)),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {
                        Text(text = "Simpan", color = Color.Black)
                    }
                }
            }
        }
    )
}

@Composable
fun DropdownField(
    label: String,
    selectedValue: String,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val options = when (label) {
        "Pilih Fakultas" -> listOf(
            "Fakultas Teknologi Informasi"
        )
        "Pilih Jurusan" -> listOf(
            "Sistem Informasi"
        )
        "Pilih Mata Kuliah" -> listOf(
            "Pemrograman Teknologi Bergerak",
            "Akuisisi Data",
            "E-Bisnis"
        )
        "Pilih Pertemuan" -> listOf(
            "Pertemuan 1",
            "Pertemuan 2",
            "Pertemuan 3",
            "Pertemuan 4",
            "Pertemuan 5"
        )
        "Pilih Waktu" -> listOf(
            "07.30-09.10",
            "13.30-16.00",
            "09.20-11.50"
        )
        "Pilih Jenis Pertemuan" -> listOf("Offline", "Online")
        else -> emptyList()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFACACAC), RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable { expanded = !expanded }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = selectedValue.ifEmpty { label }, color = Color.Black)
            Icon(Icons.Filled.ArrowDropDown, contentDescription = null, tint = Color.Black)
        }

        // Dropdown menu yang muncul di sebelah kanan
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(200.dp) // Menyesuaikan lebar dropdown
                .background(Color.White) // Background dropdown
                .align(Alignment.CenterEnd) // Letakkan dropdown di sebelah kanan
                .padding(8.dp) // Padding dropdown
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onValueChange(option)
                    expanded = false
                }) {
                    Text(text = option, color = Color.Black)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateMeetingScreen() {
    CreateMeetingScreen()
}
