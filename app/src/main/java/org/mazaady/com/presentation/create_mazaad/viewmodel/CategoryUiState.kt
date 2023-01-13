package org.mazaady.com.presentation.create_mazaad.viewmodel

import org.mazaady.com.data.category.dto.AllCategories


data class CategoryUiState(
    val isLoading: Boolean = false,
    val categoriesData: AllCategories? =null,
    val errorMessage: String? = null
)
