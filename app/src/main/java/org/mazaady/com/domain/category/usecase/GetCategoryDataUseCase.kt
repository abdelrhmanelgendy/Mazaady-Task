package org.mazaady.com.domain.category.usecase

import org.mazaady.com.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.domain.category.repository.CategoryRepository
import java.lang.Exception
import javax.inject.Inject

open  class GetCategoryDataUseCase
@Inject
constructor(
    private val repository: CategoryRepository
    ) {

    operator open fun invoke(): Flow<DataState<AllCategories>>
    = flow {
        try {
            emit(DataState.Loading())
            val allCategories = repository.getAllCategories()
            emit(DataState.Success(allCategories))
        } catch (e: Exception) {
            emit(DataState.Error("Check your internet connection!"))
        }
    }
}