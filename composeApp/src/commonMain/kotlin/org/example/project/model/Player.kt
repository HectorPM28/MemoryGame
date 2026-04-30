package org.example.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id: Long? = null,

    @SerialName("username")
    val name: String,

    val errors: Long = 0,
    val points: Long = 0
)