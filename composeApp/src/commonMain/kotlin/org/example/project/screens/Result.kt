package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.theme.AzulBoton
import org.example.project.theme.AzulClaro
import org.example.project.view.Card
import org.example.project.viewModels.MemoryViewModel

@Composable
fun ResultsScreen(navigateToMenu: () -> Unit, memoryViewModel: MemoryViewModel) {
    Column(modifier = Modifier.fillMaxSize().background(AzulClaro), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.95f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Fila de Usuario
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Player: ${memoryViewModel.user}", fontWeight = FontWeight.Bold)
                }

                // Fila de Dificultad
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Difficulty: ${memoryViewModel.difficulty}", fontWeight = FontWeight.Bold)
                }

                Divider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total points: ${memoryViewModel.points}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Errors made: ${memoryViewModel.errors}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(Modifier.height(24.dp))

        Button(onClick = {navigateToMenu()}, colors = ButtonDefaults.buttonColors(containerColor = AzulBoton)) {
            Text("Menu")
        }
    }
}