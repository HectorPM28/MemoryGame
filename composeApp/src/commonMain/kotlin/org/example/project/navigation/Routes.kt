package org.example.project.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
sealed class Route: NavKey {
    @Serializable
    data object MenuScreen : Route()
    @Serializable
    data object GameScreen : Route()
    @Serializable
    data object RankingScreen : Route()
    @Serializable
    data object DifficultyScreen : Route()
    @Serializable
    data object ResultsScreen : Route()
}
