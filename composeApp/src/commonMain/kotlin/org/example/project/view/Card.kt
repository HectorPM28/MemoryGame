package org.example.project.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import memorygame.composeapp.generated.resources.Res
import memorygame.composeapp.generated.resources.SignoInterrogacion
import org.example.project.viewModels.memoryViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Card(image: DrawableResource, memoryViewModel: memoryViewModel) {
    var click by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1.1f)
            .clickable(onClick = {click = true; click = memoryViewModel.checkImages(image)})
    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            if(click) {
                Image(
                    painter = painterResource(resource = image),
                    contentDescription = "Kirby",
                    modifier = Modifier.clip(RoundedCornerShape(25f))
                )
            }else{
                Image(
                    painter = painterResource(resource = Res.drawable.SignoInterrogacion),
                    contentDescription = "Example",
                    modifier = Modifier.clip(RoundedCornerShape(25f))
                )
            }
        }
    }
}