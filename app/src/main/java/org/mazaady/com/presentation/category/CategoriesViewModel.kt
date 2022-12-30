package org.mazaady.com.presentation.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raminabbasiiii.cleanarchitectureapp.presentation.movie.detail.CategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import org.mazaady.com.domain.usecase.GetCategoryDataUseCase
import org.mazaady.com.domain.util.DataState
import org.mazaady.com.presentation.CategoryDataEvents
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel
@Inject
constructor(
    private val getCategoryDataUseCase: GetCategoryDataUseCase,
    private val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        savedStateHandle.get<Int>("categories")?.let { movieId ->
            onEvent(CategoryDataEvents.GetCategoryData)
        }
    }

    fun onEvent(event: CategoryDataEvents) {
        when(event) {
            is CategoryDataEvents.GetCategoryData -> {
                getCategoriesData()
            }

            else -> {

            }
        }
    }

    private fun getCategoriesData() {
        job?.cancel()
        job = getCategoryDataUseCase()
            .onEach {
                    dataState ->
                when(dataState) {
                    is DataState.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is DataState.Success -> {
                        _uiState.update { it.copy(isLoading = false) }
                        _uiState.update { it.copy(errorMessage = null) }
                        dataState.data?.let { categories ->
                            _uiState.update { it.copy(categoriesData = categories) }
                        }
                    }
                    is DataState.Error -> {
                        _uiState.update { it.copy(isLoading = false) }
                        _uiState.update { it.copy(errorMessage = dataState.message) }
                    }
                }
            }.launchIn(viewModelScope)

    }


//    private fun refresh() {
//        savedStateHandle.get<Int>("movie_id")?.let { movieId ->
//            getMovieDetails(movieId)
//        }
//    }
}




















