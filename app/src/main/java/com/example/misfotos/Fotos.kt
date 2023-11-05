package com.example.misfotos

import android.provider.ContactsContract.CommonDataKinds.Photo
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Fotos(){
    Column(Modifier.fillMaxSize()) { //necesitamos esto para que el it funcione

        //Funcion para cuando cliquemos en una foto
        var selectedPicture: Photos? by remember {mutableStateOf(null)}

        //Para poder hacer swipe en la row
        LazyRow {
            //Cogermos la lista de fotos
            items(getPhoto()) { allPicture -> //Todas se llamaran así
                ItemPhoto(
                    //Seleccionamos todas las fotos
                    photos = allPicture
                ) {selectedPicture = it} //La variable se la asignamos al usuario
            }
        }
        /*Ahora creamos una fila para que, cuando seleccionemos una foto
        salga en el centro de la pantalla con un tamaño que nosotros elijamos*/

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            //La imagen que seleccione el usuario estara en esta variable
            selectedPicture?.let {//Si no es nulo el valor, se hace lo que va debajo
                //De lo que ha elegido el user, cogemos la foto
                Image(painter = painterResource(id = it.photos),
                    //Desripción (que esto da igual)
                    contentDescription = "La imagen que elija el user",
                    //Asignamos el tamañp de la foto
                    modifier = Modifier.size(500.dp).padding(5.dp))
            }
        }
    }
}
//Photos
data class Photos(@DrawableRes var photos: Int)

fun getPhoto() :List<Photos>{
    return listOf(
        Photos(R.drawable.image1),
        Photos(R.drawable.image2),
        Photos(R.drawable.image3),
        Photos(R.drawable.image4),
        Photos(R.drawable.image5),
        Photos(R.drawable.image6),
        Photos(R.drawable.image7),
        Photos(R.drawable.image8),
    )
}

@Composable
fun ItemPhoto(photos: Photos,  onItemSelected: (Photos) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onItemSelected(photos)}) {

        //Para mostrar la imagen y darle propiedades
        Image(painter = painterResource(id = photos.photos), //Cogemos el atributo photo
            contentDescription = "Pictures of the project", //Una pequeña descripción
            contentScale = ContentScale.Inside, //Escalamos la imagen dentro del espacio
            modifier = Modifier.padding(5.dp)
        )
    }
}
