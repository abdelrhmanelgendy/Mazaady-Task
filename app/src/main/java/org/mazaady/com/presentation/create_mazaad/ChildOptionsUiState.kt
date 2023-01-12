package com.raminabbasiiii.cleanarchitectureapp.presentation.movie.detail

import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryProps
import org.mazaady.com.data.network.entity.child_options_model.ChildOptions


data class ChildOptionsUiState(
    val isLoading: Boolean = false,
    val childOptions: ChildOptions?=null,
    val errorMessage: String? = null
)
