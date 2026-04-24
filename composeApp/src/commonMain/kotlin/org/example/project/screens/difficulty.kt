package org.example.project.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.viewModels.MemoryViewModel

@Composable
fun DifficultyScreen(navigateToGame: () -> Unit, memoryViewModel: MemoryViewModel) {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text("Selecciona una dificultad", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { memoryViewModel.difficulty = "Easy" },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            )
            { Text("Easy") }
            Button(onClick = { memoryViewModel.difficulty = "Medium" },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
            )
            { Text("Medium") }
            Button(onClick = { memoryViewModel.difficulty = "Hard" },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            )
            { Text("Hard") }
        }
        Button(onClick = navigateToGame) { Text("Start game") }
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = memoryViewModel.selectedText,
            onValueChange = { memoryViewModel.selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { memoryViewModel.expanded = true }
                .fillMaxWidth()
        )


        DropdownMenu(
            expanded = memoryViewModel.expanded,
            onDismissRequest = { memoryViewModel.expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
        ) {
            memoryViewModel.possibleImages.forEach { img ->
                DropdownMenuItem(
                    text = { Text(text = img) },
                    onClick = {
                        memoryViewModel.expanded = false
                        memoryViewModel.selectedText = img
                    })
            }}}
    }