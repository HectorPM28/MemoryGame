package org.example.project.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import memorygame.composeapp.generated.resources.*
import memorygame.composeapp.generated.resources.Res
import org.example.project.model.MemoryCard
import org.example.project.model.Player
import org.example.project.repository.PlayerRepository
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
    val miiImages = listOf(
        Res.drawable.Hector,
        Res.drawable.Maria,
        Res.drawable.Camila,
        Res.drawable.Yoon,
        Res.drawable.Loki,
        Res.drawable.Gumi,
        Res.drawable.Miku,
        Res.drawable.Art
    )
    var cardsForGame = mutableListOf<MemoryCard>()
    private var _firstCard: MemoryCard? = null
    fun getListForGame(): MutableList<MemoryCard>{
        this._difficultyValue.value = difficulty

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
                    delay(500)
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
    val possibleImages = listOf("Kirbo", "CursedPokemon", "Mii" )

    fun getImgForGame(): List<DrawableResource>{
        this._imageTextValue.value = selectedText
        return when (selectedText){
            "Kirbo" -> kirboImages
            "CursedPokemon" -> pokeImages
            "Mii" -> miiImages
            else -> kirboImages
        }
    }

    //Stats Game
    var user by mutableStateOf("")
    var errors : Long = 0
    var points : Long = 15
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
    fun getResultsOfRound(){
        this._userNameValue.value = user
        this._pointValue.value = (points - errors)
        this._errorValue.value = errors

        afegirPlayer(user, errors, points-errors)
    }

    //Repositori
    private val repository by lazy { PlayerRepository() }

    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val player: StateFlow<List<Player>> = _players

    init {
        carregarPlayers()
    }

    private fun carregarPlayers() {
        viewModelScope.launch {
            try {
                val lista = withContext(Dispatchers.Default) {
                    repository.obtenirPlayers()
                }
                _players.value = lista
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun afegirPlayer(name: String, errors: Long, points: Long) {
        viewModelScope.launch {
            repository.afegirPlayer(name, errors, points)
            carregarPlayers()
        }
    }

    //Testing
    private val _difficultyValue = MutableStateFlow("")
    val difficultyValue: StateFlow<String> = _difficultyValue.asStateFlow()

    fun setDifficultyText(newValue: String) {
        _difficultyValue.value = newValue
    }
    private val _imageTextValue = MutableStateFlow("")
    val imageTextValue: StateFlow<String> = _imageTextValue.asStateFlow()

    fun setImageText(newValue: String) {
        _imageTextValue.value = newValue
    }
    private val _pointValue = MutableStateFlow(0L)
    val pointValue: StateFlow<Long> = _pointValue.asStateFlow()
    fun setPointsLong(newValue: Long) {
        _pointValue.value = newValue
    }

    private val _errorValue = MutableStateFlow(0L)
    val errorValue: StateFlow<Long> = _errorValue.asStateFlow()
    fun setErrorsLong(newValue: Long) {
        _errorValue.value = newValue
    }

    private val _userNameValue = MutableStateFlow("")
    val userNameValue: StateFlow<String> = _userNameValue.asStateFlow()

    fun setUsernameText(newValue: String) {
        _userNameValue.value = newValue
    }
    fun resetValues() {
        this._difficultyValue.value = ""
        this._pointValue.value = 0L
        this._errorValue.value = 0L
        this._imageTextValue.value = ""
        this._userNameValue.value = ""
    }

}