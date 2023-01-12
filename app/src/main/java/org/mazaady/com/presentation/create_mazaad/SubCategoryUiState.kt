package com.raminabbasiiii.cleanarchitectureapp.presentation.movie.detail

import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryProps


data class SubCategoryUiState(
    val isLoading: Boolean = false,
    val subCategoryProps: SubCategoryProps?=null,
    val errorMessage: String? = null
)
