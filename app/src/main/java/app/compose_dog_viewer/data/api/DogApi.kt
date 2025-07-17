package app.compose_dog_viewer.data.api

import app.compose_dog_viewer.data.model.DogResponseDto
import retrofit2.http.GET

interface DogApi {

    @GET("breeds/image/random")
    suspend fun getRandomDog(): DogResponseDto
}