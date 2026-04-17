package org.example.project.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val navConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Route.MenuScreen::class, Route.MenuScreen.serializer())
            subclass(Route.GameScreen::class, Route.GameScreen.serializer())
            subclass(Route.ResultsScreen::class, Route.ResultsScreen.serializer())
            subclass(Route.DifficultyScreen::class, Route.DifficultyScreen.serializer())
        }
    }
}