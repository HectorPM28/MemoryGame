package org.example.project.screens

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.example.project.theme.*
import org.example.project.viewModels.MemoryViewModel

@Composable
fun DifficultyScreen(navigateToGame: () -> Unit, memoryViewModel: MemoryViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().background(AzulClaro), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text("Select a difficulty", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { memoryViewModel.difficulty = "Easy" },
                colors = ButtonDefaults.buttonColors(containerColor = AzulCeleste),
                modifier = Modifier.testTag("Easy")
            )
            { Text("Easy") }
            Button(
                onClick = { memoryViewModel.difficulty = "Medium" },
                colors = ButtonDefaults.buttonColors(containerColor = AzulBoton),
                modifier = Modifier.testTag("Medium")
            )
            { Text("Medium") }
            Button(
                onClick = { memoryViewModel.difficulty = "Hard" },
                colors = ButtonDefaults.buttonColors(containerColor = AzulOscuro),
                modifier = Modifier.testTag("Hard")
            )
            { Text("Hard") }
        }
        Spacer(Modifier.height(100.dp))
        Button(onClick = navigateToGame, colors = ButtonDefaults.buttonColors(containerColor = AzulBoton)) { Text("Start game") }
    }
}