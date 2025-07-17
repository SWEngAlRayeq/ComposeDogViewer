package app.compose_dog_viewer.di

import app.compose_dog_viewer.data.api.DogApi
import app.compose_dog_viewer.data.repository.DogRepoImpl
import app.compose_dog_viewer.domain.repository.DogRepository
import app.compose_dog_viewer.domain.usecase.GetRandomDogUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://dog.ceo/api/"


    @Provides
    @Singleton
    fun provideDogApi(): DogApi {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApi::class.java)
    }


    @Provides
    @Singleton
    fun provideDogRepository(api: DogApi): DogRepository {
        return DogRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetRandomDogUseCase(repository: DogRepository): GetRandomDogUseCase {
        return GetRandomDogUseCase(repository)
    }




}