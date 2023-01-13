package org.mazaady.com.presentation.create_mazaad.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import org.mazaady.com.domain.category.usecase.GetCategoryDataUseCase
import org.mazaady.com.domain.child_options.usecase.GetChildOptionsByIdUseCase
import org.mazaady.com.domain.subCategory.usecase.GetSubCategoryPropsUseCase
import org.mazaady.com.domain.util.DataState
import javax.inject.Inject

@HiltViewModel
class CreateMazaadViewModel
@Inject
constructor(
    private val getCategoryDataUseCase: GetCategoryDataUseCase,
    private val getSubCategoryPropsUseCase: GetSubCategoryPropsUseCase,
    private val getChildOptionsByIdUseCase: GetChildOptionsByIdUseCase,
    private val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()


    private val _subCategoryPropsUiState = MutableStateFlow(SubCategoryUiState())
    val subCategoryPropsUiState: StateFlow<SubCategoryUiState> = _subCategoryPropsUiState.asStateFlow()

    private val _childOptionsUiState = MutableStateFlow(ChildOptionsUiState())
    val childOptionsUiState: StateFlow<ChildOptionsUiState> = _childOptionsUiState.asStateFlow()


    private var job: Job? = null

    init {
        savedStateHandle.get<Int>("categories")?.let {
            onEvent(CreateMazaadDataEvents.GetCreateMazaadData)
        }
    }

    fun onEvent(event: CreateMazaadDataEvents) = when(event) {
        is CreateMazaadDataEvents.GetCreateMazaadData -> {
            getCategoriesData()
        }
        is CreateMazaadDataEvents.GetSubCreateMazaadProps -> {
            getSubCategoryPropsData(event.id)
        }
        is CreateMazaadDataEvents.GetChildOptions -> {
            getChildOptions(event.id)
        }

        else -> {

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

    private fun getChildOptions(id: String) {
        job?.cancel()
        job = getChildOptionsByIdUseCase(id)
            .onEach {
                    dataState ->
                when(dataState) {
                    is DataState.Loading -> {
                        _childOptionsUiState.update { it.copy(isLoading = true) }
                    }
                    is DataState.Success -> {
                        _childOptionsUiState.update { it.copy(isLoading = false) }
                        _childOptionsUiState.update { it.copy(errorMessage = null) }
                        dataState.data?.let { childOptions ->
                            _childOptionsUiState.update { it.copy(childOptions = childOptions) }
                        }
                    }
                    is DataState.Error -> {
                        _childOptionsUiState.update { it.copy(isLoading = false) }
                        _childOptionsUiState.update { it.copy(errorMessage = dataState.message) }
                    }
                }
            }.launchIn(viewModelScope)

    }


}




















