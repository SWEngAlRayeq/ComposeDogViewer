package app.compose_dog_viewer.data.model

import app.compose_dog_viewer.domain.model.DogImage

data class DogResponseDto(
    val message:String,
    val status:String
){
    fun toDogImage() = DogImage(imageUrl = message)
}
