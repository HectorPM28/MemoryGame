package org.example.project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.view.Card
import org.example.project.viewModels.memoryViewModel

@Composable
fun GameScreen(navigateToResults: () -> Unit, memoryViewModel: memoryViewModel) {
    val dificulty = memoryViewModel.dificulty
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Juego $dificulty", style = MaterialTheme.typography.headlineMedium)
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        ) {
            for(imagen in memoryViewModel.getListForGame()){
                item{
                    Card(imagen, memoryViewModel)
                }
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = navigateToResults) { Text("Results") }
    }
}
