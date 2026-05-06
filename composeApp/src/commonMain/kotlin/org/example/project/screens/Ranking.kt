package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.model.Player
import org.example.project.model.PlayerRow
import org.example.project.theme.AzulBoton
import org.example.project.theme.AzulClaro
import org.example.project.viewModels.MemoryViewModel

@Composable
fun RankingScreen(navigateToMenu: () -> Unit, memoryViewModel: MemoryViewModel) {
    val llistPlayers by memoryViewModel.player.collectAsStateWithLifecycle(initialValue = emptyList<Player>())
    Column(modifier = Modifier.fillMaxSize().background(AzulClaro), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "RANKING",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Black,
        )
        Spacer(Modifier.height(24.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.fillMaxWidth()
                .weight(1f, fill = false),
            horizontalArrangement = Arrangement.Center
        ) {
            items(llistPlayers     , key = { it.id!! }) { player ->
                PlayerRow(player)
            }
        }
        Spacer(Modifier.height(24.dp))

        Button(onClick = navigateToMenu, colors = ButtonDefaults.buttonColors(containerColor = AzulBoton)) { Text("Volver") }
    }
}

