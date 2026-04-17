package org.example.project.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import memorygame.composeapp.generated.resources.KirbyCruz
import memorygame.composeapp.generated.resources.KirbyCulo
import memorygame.composeapp.generated.resources.KirbyEmputado
import memorygame.composeapp.generated.resources.KirbyEstrenyido
import memorygame.composeapp.generated.resources.KirbyGritando
import memorygame.composeapp.generated.resources.KirbyObservador
import memorygame.composeapp.generated.resources.KirbyPistola
import memorygame.composeapp.generated.resources.KirbyVaquero
import memorygame.composeapp.generated.resources.Res
import org.example.project.model.MemoryCard
import org.jetbrains.compose.resources.DrawableResource


class MemoryViewModel(){
    var dificulty: String = "Easy"

    val allImages = listOf(
        Res.drawable.KirbyCruz,
        Res.drawable.KirbyVaquero,
        Res.drawable.KirbyEstrenyido,
        Res.drawable.KirbyGritando,
        Res.drawable.KirbyCulo,
        Res.drawable.KirbyEmputado,
        Res.drawable.KirbyObservador,
        Res.drawable.KirbyPistola
    )
    fun getListForGame(): List<MemoryCard>{
        return when (dificulty){
            "Easy" -> createList(3)
            "Medium" -> createList(5)
            "Hard" -> createList(7)
            else -> createList(3)
        }
    }
    fun createList(num: Int): List<MemoryCard>{
        val newList = mutableListOf<MemoryCard>()
        var id = 0
        for(i in 0..num){
            val img = allImages[i]
            newList.add(MemoryCard(id++, img))
            newList.add(MemoryCard(id++, img))
        }
        newList.shuffle()
        return newList
    }

    var firstCard: MemoryCard? = null
    var indexOfFirstCard: Int = 0
    fun checkImages(card: MemoryCard) {
        val currentIndex = cardsForGame.indexOfFirst { it.id == card.id }
        val newList = cardsForGame.toMutableList()
        newList[currentIndex] = newList[currentIndex].copy(isRevealed = true)

        if (firstCard == null) {
            cardsForGame = newList
            firstCard = newList[currentIndex]
            indexOfFirstCard = currentIndex
        } else {
            if (firstCard?.image == card.image) {
                cardsForGame = newList
                firstCard = null
            } else {
                newList[indexOfFirstCard] = newList[indexOfFirstCard].copy(isRevealed = false)
                newList[currentIndex] = newList[currentIndex].copy(isRevealed = false)
                cardsForGame = newList
                firstCard = null
            }
        }
    }
    var cardsForGame by mutableStateOf(listOf<MemoryCard>())

    fun prepareGame() {
        cardsForGame = getListForGame()
    }
}