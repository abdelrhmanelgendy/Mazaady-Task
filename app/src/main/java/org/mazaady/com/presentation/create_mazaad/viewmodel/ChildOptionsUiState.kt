package org.mazaady.com.presentation.create_mazaad.viewmodel

import org.mazaady.com.data.child_options.dto.ChildOptions


data class ChildOptionsUiState(
    val isLoading: Boolean = false,
    val childOptions: ChildOptions?=null,
    val errorMessage: String? = null
)
