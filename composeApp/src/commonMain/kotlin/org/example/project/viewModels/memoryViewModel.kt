package org.example.project.viewModels

import memorygame.composeapp.generated.resources.KirbyCruz
import memorygame.composeapp.generated.resources.KirbyCulo
import memorygame.composeapp.generated.resources.KirbyEmputado
import memorygame.composeapp.generated.resources.KirbyEstrenyido
import memorygame.composeapp.generated.resources.KirbyGritando
import memorygame.composeapp.generated.resources.KirbyObservador
import memorygame.composeapp.generated.resources.KirbyPistola
import memorygame.composeapp.generated.resources.KirbyVaquero
import memorygame.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.DrawableResource


class memoryViewModel(){
    var dificulty: String = "Easy"

    val images = listOf(
        Res.drawable.KirbyCruz,
        Res.drawable.KirbyVaquero,
        Res.drawable.KirbyEstrenyido,
        Res.drawable.KirbyGritando,
        Res.drawable.KirbyCulo,
        Res.drawable.KirbyEmputado,
        Res.drawable.KirbyObservador,
        Res.drawable.KirbyPistola
    )
    fun getListForGame(): List<DrawableResource>{
        when (dificulty){
            "Easy" -> return createList(3)
            "Medium" -> return createList(5)
            "Hard" -> return createList(7)
        }
        return images
    }
    fun createList(num: Int): List<DrawableResource>{
        val newList = mutableListOf<DrawableResource>()
       for(i in 0..num){
           newList.add(images[i])
       }
        for(i in 0..num){
            newList.add(images[i])
        }
        newList.shuffle()
        return  newList
    }

    var imageChoosen1: DrawableResource? = null
    var imageChoosen2: DrawableResource? = null

    fun checkImages(image: DrawableResource): Boolean{
        imageChoosen1 = image

        if(imageChoosen2 == null){
            imageChoosen2 = imageChoosen1
            return true
        }
        if(imageChoosen1 != imageChoosen2){
            return false
        }
        imageChoosen1 = null
        imageChoosen2 = null
        return true
    }
}