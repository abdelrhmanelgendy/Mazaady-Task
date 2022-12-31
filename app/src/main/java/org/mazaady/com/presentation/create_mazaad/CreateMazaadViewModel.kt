package org.mazaady.com.presentation.create_mazaad

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raminabbasiiii.cleanarchitectureapp.presentation.movie.detail.CategoryUiState
import com.raminabbasiiii.cleanarchitectureapp.presentation.movie.detail.SubCategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import org.mazaady.com.domain.usecase.GetCategoryDataUseCase
import org.mazaady.com.domain.usecase.GetSubCategoryPropsUseCase
import org.mazaady.com.domain.util.DataState
import org.mazaady.com.presentation.CreateMazaadDataEvents
import javax.inject.Inject

@HiltViewModel
class CreateMazaadViewModel
@Inject
constructor(
    private val getCategoryDataUseCase: GetCategoryDataUseCase,
    private val getSubCategoryPropsUseCase: GetSubCategoryPropsUseCase,
    private val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()


    private val _subCategoryPropsUiState = MutableStateFlow(SubCategoryUiState())
    val subCategoryPropsUiState: StateFlow<SubCategoryUiState> = _subCategoryPropsUiState.asStateFlow()
    private var job: Job? = null

    init {
        savedStateHandle.get<Int>("categories")?.let {
            onEvent(CreateMazaadDataEvents.GetCreateMazaadData)
        }
    }

    fun onEvent(event: CreateMazaadDataEvents) {
        when(event) {
            is CreateMazaadDataEvents.GetCreateMazaadData -> {
                getCategoriesData()
            }
            is CreateMazaadDataEvents.GetSubCreateMazaadProps -> {
                getSubCategoryPropsData(event.id)
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
    private fun getSubCategoryPropsData(id: String) {
        Log.d("TAG665", "getSubCategoryPropsData: ")
        job?.cancel()
        job = getSubCategoryPropsUseCase(id)
            .onEach {
                    dataState ->
                when(dataState) {
                    is DataState.Loading -> {
                        _subCategoryPropsUiState.update { it.copy(isLoading = true) }
                    }
                    is DataState.Success -> {
                        _subCategoryPropsUiState.update { it.copy(isLoading = false) }
                        _subCategoryPropsUiState.update { it.copy(errorMessage = null) }
                        dataState.data?.let { subCategories ->
                            _subCategoryPropsUiState.update { it.copy(subCategoryProps = subCategories) }
                        }
                    }
                    is DataState.Error -> {
                        _subCategoryPropsUiState.update { it.copy(isLoading = false) }
                        _subCategoryPropsUiState.update { it.copy(errorMessage = dataState.message) }
                    }
                }
            }.launchIn(viewModelScope)

    }



}



















