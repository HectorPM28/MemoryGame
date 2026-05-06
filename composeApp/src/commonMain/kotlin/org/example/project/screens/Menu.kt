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

@Composable
fun MenuScreen(navigateToDifficulty: () -> Unit, navigateToRanking: () -> Unit, memoryViewModel: MemoryViewModel) {
    Column(modifier = Modifier.fillMaxSize().background(AzulClaro), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text("Memory Game", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        TextField(value = memoryViewModel.user, onValueChange = { memoryViewModel.user = it })
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = memoryViewModel.selectedText,
            onValueChange = { memoryViewModel.selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { memoryViewModel.expanded = true }
                .width(200.dp),
            colors = OutlinedTextFieldDefaults.colors(disabledTextColor = Color.White, disabledContainerColor = AzulBoton,)
        )
        DropdownMenu(
            expanded = memoryViewModel.expanded,
            onDismissRequest = { memoryViewModel.expanded = false },
            modifier = Modifier
                .width(200.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
        ) {
            memoryViewModel.possibleImages.forEach { img ->
                DropdownMenuItem(
                    text = { Text(text = img) },
                    onClick = {
                        memoryViewModel.expanded = false
                        memoryViewModel.selectedText = img
                    })
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
