package org.example.project.model

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id: Int? = null,
    val name: String,
    val errors: Int,
    val points: Int
)