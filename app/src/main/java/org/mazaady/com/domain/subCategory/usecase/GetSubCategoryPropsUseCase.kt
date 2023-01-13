package org.mazaady.com.domain.subCategory.usecase

import org.mazaady.com.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mazaady.com.data.subCategory.dto.SubCategoryProps
 import org.mazaady.com.domain.subCategory.repository.SubCategoryRepository
import java.lang.Exception
import javax.inject.Inject

open  class GetSubCategoryPropsUseCase
@Inject
constructor(
    private val subCategoryRepository: SubCategoryRepository
    ) {

    operator open   fun invoke(id:String): Flow<DataState<SubCategoryProps>>
    = flow {
        try {
            emit(DataState.Loading())
            val allCategories = subCategoryRepository.getSubCategoryProps(id)
            emit(DataState.Success(allCategories))
        } catch (e: Exception) {
            emit(DataState.Error("Check your internet connection!"))
        }
    }
}