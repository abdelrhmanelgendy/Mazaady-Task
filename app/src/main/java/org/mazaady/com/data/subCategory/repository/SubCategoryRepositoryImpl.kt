package org.mazaady.com.data.subCategory.repository

import org.mazaady.com.data.subCategory.dto.SubCategoryProps
import org.mazaady.com.data.subCategory.api.SubCategoryApi
import org.mazaady.com.domain.subCategory.repository.SubCategoryRepository
import javax.inject.Inject


class SubCategoryRepositoryImpl
@Inject constructor(
    private val api: SubCategoryApi,

    ) : SubCategoryRepository {
    override suspend fun getSubCategoryProps(id: String): SubCategoryProps {
        return api.getSubCategoryProps(id)
    }


}