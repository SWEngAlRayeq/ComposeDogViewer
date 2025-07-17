package app.compose_dog_viewer.data.repository

import app.compose_dog_viewer.data.api.DogApi
import app.compose_dog_viewer.domain.model.DogImage
import app.compose_dog_viewer.domain.repository.DogRepository
import javax.inject.Inject

class DogRepoImpl @Inject constructor(
    private val dogApi: DogApi
) : DogRepository{
    override suspend fun getRandomDogImage(): DogImage {
        return dogApi.getRandomDog().toDogImage()
    }
}