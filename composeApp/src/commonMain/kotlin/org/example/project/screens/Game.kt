package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.theme.AzulClaro
import org.example.project.view.Card
import org.example.project.viewModels.MemoryViewModel

@Composable
fun GameScreen(navigateToResults: () -> Unit, memoryViewModel: MemoryViewModel, navigateToMenu: () -> Unit) {
    val lista = remember { memoryViewModel.prepareGame() }

    Column(
        modifier = Modifier.fillMaxSize().background(AzulClaro), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${memoryViewModel.difficulty} game",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Black,
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        ) {
            items(lista, key = { it.id }) { imagen ->
                Card(imagen, memoryViewModel, lista, navigateToResults)
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = navigateToMenu){ Text("Return to menu") }
    }
}