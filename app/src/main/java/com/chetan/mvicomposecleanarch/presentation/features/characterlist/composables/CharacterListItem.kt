package com.chetan.mvicomposecleanarch.presentation.features.characterlist.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.chetan.mvicomposecleanarch.R
import com.chetan.mvicomposecleanarch.data.model.Location
import com.chetan.mvicomposecleanarch.data.model.Origin
import com.chetan.mvicomposecleanarch.data.model.Result

@Composable
fun CharacterItemCard(character: Result, onCharacterItemClick : (character: Result) -> Unit){
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth().padding(
            vertical = dimensionResource(R.dimen.padding_smalll),
            horizontal = dimensionResource(R.dimen.padding_smalll)
        ).clickable {
            onCharacterItemClick(character)
        },
        elevation = CardDefaults.cardElevation( defaultElevation = dimensionResource(R.dimen.charater_item_card_elevation))
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(dimensionResource(R.dimen.padding_large))

        ){
            AsyncImage(
                placeholder = painterResource(R.drawable.ic_launcher_background),
                model = character.image,
                contentDescription = "charater image",
                modifier = Modifier.size(dimensionResource(R.dimen.character_item_image_size)).
                padding(end = dimensionResource(R.dimen.padding_large))
                ,error = painterResource(R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop

            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_smalll)))
            Text(
                text = character.name,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_large))
            )

        }
    }
}
@Preview(showBackground = true)
@Composable
fun CharacterItemCardPreview(){
    CharacterItemCard(character = Result("", emptyList(), "", 0, "", Location("", ""), "Charater1", Origin("", ""), "", "", "", ""), onCharacterItemClick = {})
}