package org.example.project.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import memorygame.composeapp.generated.resources.*
import memorygame.composeapp.generated.resources.Res
import org.example.project.model.MemoryCard
import org.jetbrains.compose.resources.DrawableResource

class MemoryViewModel(): ViewModel(){
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
    fun getListForGame(): MutableList<MemoryCard>{
        return when (dificulty){
            "Easy" -> createList(3)
            "Medium" -> createList(5)
            "Hard" -> createList(7)
            else -> createList(3)
        }
    }
    fun createList(num: Int): MutableList<MemoryCard>{
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
    var cardsForGame = mutableListOf<MemoryCard>()
    fun prepareGame() : MutableList<MemoryCard>{
        cardsForGame = getListForGame()
        return cardsForGame
    }
    fun changeCardState(lista: MutableList<MemoryCard>, card: MemoryCard): Boolean{
        lista.forEach { memorycard ->
            if (memorycard.id == card.id){
                memorycard.isRevealed = !memorycard.isRevealed
                return memorycard.isRevealed
            }
        }
        return true
    }
    var firstCard: MemoryCard? = null
    fun checkCorrectcard(card: MemoryCard, lista: MutableList<MemoryCard>){
        if(firstCard == null){
            firstCard = card
        }else{
            if(card.image != firstCard!!.image){
                viewModelScope.launch {
                    delay(1000)
                    changeCardState(lista, card)
                    changeCardState(lista, firstCard!!)
                    firstCard = null
                }
            }else{
                firstCard = null
            }
        }
    }
}