package org.example.project.model

import org.jetbrains.compose.resources.DrawableResource

data class MemoryCard(
    val id: Int,
    val image: DrawableResource,
    var isRevealed: Boolean = false
)