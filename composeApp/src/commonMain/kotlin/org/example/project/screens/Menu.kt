package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import memorygame.composeapp.generated.resources.KirbyCruz
import memorygame.composeapp.generated.resources.Res
import org.example.project.theme.AzulBoton
import org.example.project.theme.AzulClaro
import org.example.project.viewModels.MemoryViewModel
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navigateToDifficulty: () -> Unit, navigateToRanking: () -> Unit, memoryViewModel: MemoryViewModel) {
    Column(modifier = Modifier.fillMaxSize().background(AzulClaro), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text("Memory Game", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        TextField(value = memoryViewModel.user, onValueChange = { memoryViewModel.user = it })
        Spacer(Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = memoryViewModel.expanded,
            onExpandedChange = { memoryViewModel.expanded = !memoryViewModel.expanded }
        ) {
            OutlinedTextField(
                value = memoryViewModel.selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = memoryViewModel.expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .width(200.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = AzulBoton,
                    unfocusedContainerColor = AzulBoton,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.DarkGray
                )
            )

            ExposedDropdownMenu(
                expanded = memoryViewModel.expanded,
                onDismissRequest = { memoryViewModel.expanded = false },
                modifier = Modifier.background(Color.White)
            ) {
                memoryViewModel.possibleImages.forEach { img ->
                    DropdownMenuItem(
                        text = { Text(text = img) },
                        onClick = {
                            memoryViewModel.selectedText = img
                            memoryViewModel.expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
        Spacer(Modifier.height(24.dp))

        Button(onClick = {
            if (memoryViewModel.user.isNotBlank()) {
                navigateToDifficulty()
            }; memoryViewModel.resetList()
        },
            colors = ButtonDefaults.buttonColors(containerColor = AzulBoton))
        { Text("Play") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = {navigateToRanking()}, colors = ButtonDefaults.buttonColors(containerColor = AzulBoton)) { Text("Ranking") }

    }
}
