package org.example.project.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://ooaaqeqmbanmlgivutir.supabase.co",
        supabaseKey = "sb_publishable_8-bUlBPeQs4MwQybudxmxg_ZHse3xCf"
    ) {
        install(Postgrest)
    }
}
