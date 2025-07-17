package app.compose_dog_viewer.domain.repository

import app.compose_dog_viewer.domain.model.DogImage

interface DogRepository {
    suspend fun getRandomDogImage(): DogImage
}