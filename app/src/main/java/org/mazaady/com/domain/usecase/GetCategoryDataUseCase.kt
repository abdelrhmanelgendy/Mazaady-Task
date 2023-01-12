package org.mazaady.com.domain.usecase

import org.mazaady.com.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.domain.repository.MazaadPropertyRepository
import java.lang.Exception
import javax.inject.Inject

class GetCategoryDataUseCase
@Inject
constructor(
    private val mazaadPropertyRepository: MazaadPropertyRepository
    ) {

    operator fun invoke(): Flow<DataState<AllCategories>>
    = flow {
        try {
            emit(DataState.Loading())
            val allCategories = mazaadPropertyRepository.getAllCategories()
            emit(DataState.Success(allCategories))
        } catch (e: Exception) {
            emit(DataState.Error("Check your internet connection!"))
        }
    }
}