package app.compose_dog_viewer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.compose_dog_viewer.domain.model.DogImage
import app.compose_dog_viewer.domain.usecase.GetRandomDogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val useCase: GetRandomDogUseCase
) : ViewModel() {

    private val _dog = MutableStateFlow<DogImage?>(null)
    val dog: StateFlow<DogImage?> = _dog

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    init {
        getRandomDog()
    }

     fun getRandomDog() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _dog.value = useCase()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

}