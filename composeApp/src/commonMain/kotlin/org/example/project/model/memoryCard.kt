package org.example.project.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.jetbrains.compose.resources.DrawableResource

class MemoryCard(
    val id: Int,
    val image: DrawableResource,
    initialIsRevealed: Boolean = false
) {
    var isRevealed by mutableStateOf(initialIsRevealed)
}