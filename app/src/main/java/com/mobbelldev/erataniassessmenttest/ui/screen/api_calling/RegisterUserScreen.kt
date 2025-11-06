package com.mobbelldev.erataniassessmenttest.ui.screen.api_calling

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobbelldev.erataniassessmenttest.ui.component.DropdownFieldComponent
import com.mobbelldev.erataniassessmenttest.ui.screen.api_calling.api.ApiClient
import com.mobbelldev.erataniassessmenttest.ui.screen.api_calling.model.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun RegisterUserScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    val genderOptions = listOf("male", "female")
    var status by remember { mutableStateOf("") }
    val statusOptions = listOf("active", "inactive")
    var isLoading by remember { mutableStateOf(false) }

    RegisterUserContent(
        modifier = modifier.fillMaxSize(),
        name = name,
        email = email,
        gender = gender,
        status = status,
        isLoading = isLoading,
        onNameChange = { name = it },
        onEmailChange = { email = it },
        onGenderChange = { gender = it },
        onStatusChange = { status = it },
        genderOptions = genderOptions,
        statusOptions = statusOptions,
        onSubmit = {
            if (name.isBlank() || email.isBlank() || gender.isBlank() || status.isBlank()) {
                scope.launch {
                    snackbarHostState.showSnackbar("Tidak boleh kosong, harap di isi!")
                }
                return@RegisterUserContent
            }

            isLoading = true
            scope.launch(Dispatchers.IO) {
                try {
                    val newUser = UserResponse(
                        name = name,
                        email = email,
                        gender = gender,
                        status = status
                    )
                    val response = ApiClient.api.registerUser(newUser)

                    withContext(Dispatchers.Main) {
                        snackbarHostState.showSnackbar("Berhasil mendaftar: ${response.name}")
                        name = ""
                        email = ""
                        gender = ""
                        status = ""
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        snackbarHostState.showSnackbar("Gagal mendaftar: ${e.message}")
                    }
                } finally {
                    withContext(Dispatchers.Main) {
                        isLoading = false
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegisterUserContent(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    gender: String,
    onGenderChange: (String) -> Unit,
    genderOptions: List<String>,
    status: String,
    onStatusChange: (String) -> Unit,
    statusOptions: List<String>,
    isLoading: Boolean,
    onSubmit: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { onNameChange(it) },
                label = { Text("Name") }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { onEmailChange(it) },
                label = { Text("Email") }
            )
            DropdownFieldComponent(
                label = "Gender",
                options = genderOptions,
                selectedOption = gender,
                onOptionSelected = onGenderChange
            )
            DropdownFieldComponent(
                label = "Status",
                options = statusOptions,
                selectedOption = status,
                onOptionSelected = onStatusChange
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSubmit() },
                enabled = !isLoading
            ) {
                Text(text = if (isLoading) "Loading..." else "Submit")
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}