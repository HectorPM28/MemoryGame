package org.example.project.screens

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.model.Player
import org.example.project.viewModels.MemoryViewModel

@Composable
fun RankingScreen(navigateToMenu: () -> Unit, memoryViewModel: MemoryViewModel) {
    val llistPlayers by memoryViewModel.player.collectAsStateWithLifecycle(initialValue = emptyList<Player>())
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Resultados", style = MaterialTheme.typography.headlineMedium)
        Text("NAME POINTS ERRORS")
        Spacer(Modifier.height(24.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.fillMaxWidth()
                .weight(1f, fill = false),
            horizontalArrangement = Arrangement.Center
        ) {
            items(llistPlayers     , key = { it.id!! }) { player ->
                Text("${player.name} - ${player.points} - ${player.errors}", textAlign = TextAlign.Center)
            }
        }
        Button(onClick = navigateToMenu) { Text("Volver") }
    }
}

