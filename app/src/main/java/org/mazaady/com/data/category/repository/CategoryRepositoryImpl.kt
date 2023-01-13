package org.mazaady.com.data.category.repository

import org.mazaady.com.data.category.api.CategoryApi
 import org.mazaady.com.data.category.dto.AllCategories
 import org.mazaady.com.domain.category.repository.CategoryRepository
 import javax.inject.Inject


class CategoryRepositoryImpl
@Inject constructor(
    private val api: CategoryApi,

    ) : CategoryRepository {
    override suspend fun getAllCategories(): AllCategories {

        return api.getAllCategoryData()
    }


}