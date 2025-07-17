package app.compose_dog_viewer.domain.usecase

import app.compose_dog_viewer.domain.model.DogImage
import app.compose_dog_viewer.domain.repository.DogRepository
import javax.inject.Inject

class GetRandomDogUseCase @Inject constructor(
    private val repository: DogRepository
) {
    suspend operator fun invoke(): DogImage {
        return repository.getRandomDogImage()
    }
}