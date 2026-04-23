package org.example.project.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import memorygame.composeapp.generated.resources.*
import memorygame.composeapp.generated.resources.Res
import org.example.project.model.MemoryCard
import org.jetbrains.compose.resources.DrawableResource

class MemoryViewModel(): ViewModel(){
    var difficulty: String = "Easy"

    val kirboImages = listOf(
        Res.drawable.KirbyCruz,
        Res.drawable.KirbyVaquero,
        Res.drawable.KirbyEstrenyido,
        Res.drawable.KirbyGritando,
        Res.drawable.KirbyCulo,
        Res.drawable.KirbyEmputado,
        Res.drawable.KirbyObservador,
        Res.drawable.KirbyPistola
    )
    val pokeImages = listOf(
        Res.drawable.browt,
        Res.drawable.charizard,
        Res.drawable.eevee,
        Res.drawable.gibble,
        Res.drawable.mrMime,
        Res.drawable.patas,
        Res.drawable.quaxly,
        Res.drawable.spheal
    )
    fun getListForGame(): MutableList<MemoryCard>{
        return when (difficulty){
            "Easy" -> createList(3)
            "Medium" -> createList(5)
            "Hard" -> createList(7)
            else -> createList(3)
        }
    }
    fun getImgForGame(): List<DrawableResource>{
        return when (selectedText){
            "Kirbo" -> kirboImages
            "CursedPokemon" -> pokeImages
            else -> kirboImages
        }
    }
    fun createList(num: Int): MutableList<MemoryCard>{
        val newList = mutableListOf<MemoryCard>()
        var id = 0
        val listImg = getImgForGame()
        for(i in 0..num){
            val img = listImg[i]
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

    var selectedText by mutableStateOf("Kirbo")
    var expanded by mutableStateOf(false)
    val possibleImages = listOf("Kirbo", "CursedPokemon" )
}