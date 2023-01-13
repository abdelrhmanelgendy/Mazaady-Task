package org.mazaady.com.presentation.create_mazaad.viewmodel

import org.mazaady.com.data.subCategory.dto.SubCategoryProps


data class SubCategoryUiState(
    val isLoading: Boolean = false,
    val subCategoryProps: SubCategoryProps?=null,
    val errorMessage: String? = null
)
