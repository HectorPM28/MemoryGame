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
import org.example.project.model.Player
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
    var cardsForGame = mutableListOf<MemoryCard>()
    private var _firstCard: MemoryCard? = null
    fun getListForGame(): MutableList<MemoryCard>{
         when (difficulty){
            "Easy" -> {
                points = 15
                return createList(3)
            }
            "Medium" -> {
                points = 25
                return createList(5)
            }
            "Hard" -> {
                points = 50
                return createList(7)
            }
        }
        return createList(0)
    }
    var id = 0
    fun createList(num: Int): MutableList<MemoryCard>{
        val newList = mutableListOf<MemoryCard>()
        val listImg = getImgForGame()
        for(i in 0..num){
            val img = listImg[i]
            newList.add(MemoryCard(id++, img))
            newList.add(MemoryCard(id++, img))
        }
        newList.shuffle()
        return newList
    }
    fun prepareGame() : MutableList<MemoryCard>{
        cardsForGame = getListForGame()
        return cardsForGame
    }
    fun changeCardState(lista: MutableList<MemoryCard>, card: MemoryCard){
        lista.forEach { memorycard ->
            if (memorycard.id == card.id) memorycard.isRevealed = !memorycard.isRevealed
        }
    }
    fun checkCorrectcard(card: MemoryCard, lista: MutableList<MemoryCard>){
        if(_firstCard == null){
            _firstCard = card
        }else{
            if(card.image != _firstCard!!.image){
                viewModelScope.launch {
                    delay(1000)
                    changeCardState(lista, card)
                    changeCardState(lista, _firstCard!!)
                    _firstCard = null
                    errors++
                }
            }else{
                _firstCard = null
                pairs++
            }
        }
    }
    fun resetList(){
        cardsForGame.clear()
        errors = 0
        pairs = 0
    }

    //Selecting Images
    var selectedText by mutableStateOf("Kirbo")
    var expanded by mutableStateOf(false)
    val possibleImages = listOf("Kirbo", "CursedPokemon" )

    fun getImgForGame(): List<DrawableResource>{
        return when (selectedText){
            "Kirbo" -> kirboImages
            "CursedPokemon" -> pokeImages
            else -> kirboImages
        }
    }

    //Stats Game
    var errors = 0
    var players = mutableListOf<Player>()
    var points = 15
    var playerName = "Juan"
    var pairs = 0

    fun checkEndOfRound(): Boolean{
        when (difficulty){
            "Easy" -> if(pairs > 3){
                getResultsOfRound()
                return true
            }
            "Medium" -> if(pairs > 5){
                getResultsOfRound()
                return true
            }
            "Hard" -> if(pairs > 7){
                getResultsOfRound()
                return true
            }
        }
        return false
    }
    var idPlayer = 1
    fun getResultsOfRound(){
        players.add(Player(idPlayer++, playerName, errors, points - errors))
    }
}