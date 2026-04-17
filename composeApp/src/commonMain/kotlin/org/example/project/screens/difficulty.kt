package org.example.project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.viewModels.memoryViewModel

@Composable
fun DifficultyScreen(navigateToGame: () -> Unit, memoryViewModel: memoryViewModel) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Selecciona una dificultad", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
        Button(onClick = { memoryViewModel.dificulty = "Easy" }){ Text("Easy")}
        Button(onClick = { memoryViewModel.dificulty = "Medium" }){ Text("Mediun")}
        Button(onClick = { memoryViewModel.dificulty = "Hard" }){ Text("Hard")}
        Spacer(Modifier.height(24.dp))
        Button(onClick = navigateToGame) { Text("Start game") }
    }
}