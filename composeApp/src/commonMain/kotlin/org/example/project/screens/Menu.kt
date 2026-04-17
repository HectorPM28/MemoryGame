package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import memorygame.composeapp.generated.resources.KirbyCruz
import memorygame.composeapp.generated.resources.Res
import org.example.project.viewModels.MemoryViewModel
import org.jetbrains.compose.resources.painterResource

@Composable
fun MenuScreen(navigateToDifficulty: () -> Unit, memoryViewModel: MemoryViewModel) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text("Memory Game", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
        Image(
            painter = painterResource(resource = Res.drawable.KirbyCruz),
            contentDescription = "Example",
            modifier = Modifier.clip(RoundedCornerShape(25f))
        )

        Button(onClick = navigateToDifficulty) { Text("Ir a Dificultades") }
        Spacer(Modifier.height(8.dp))
    }
}
