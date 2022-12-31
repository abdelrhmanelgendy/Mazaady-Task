package com.raminabbasiiii.cleanarchitectureapp.presentation.movie.detail

import org.mazaady.com.data.network.dto.category_models.AllCategories


data class CategoryUiState(
    val isLoading: Boolean = false,
    val categoriesData: AllCategories? =null,
    val errorMessage: String? = null
)
