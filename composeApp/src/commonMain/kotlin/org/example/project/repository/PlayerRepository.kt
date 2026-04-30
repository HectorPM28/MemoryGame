package org.example.project.repository

import org.example.project.network.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import org.example.project.model.Player

class PlayerRepository {
    private val player = SupabaseClient.client.postgrest["Players"]
    suspend fun afegirPlayer(name: String, errors: Long, points: Long) {
        val nouPlayer =
            Player(name = name, errors = errors, points = points)
        player.insert(nouPlayer)
    }

    suspend fun obtenirPlayers(): List<Player> {
        return player.select().decodeList<Player>()
    }

    suspend fun esborrarMarcador(id: Long) {
        player.delete {
            filter {
                eq("id", id)
            }
        }
    }
    suspend fun actualitzarMarker(id: Long, newName: String) {
        player.update({
            set("username", newName)
        }) {
            filter {
                eq("id", id)
            }
        }
    }
}