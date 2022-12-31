package org.mazaady.com.data.repository

import org.mazaady.com.data.network.MazaadApi
import org.mazaady.com.data.network.dto.category_models.AllCategories
import org.mazaady.com.data.network.subcategory_props_model.SubCategoryProps
import org.mazaady.com.domain.repository.MazaadPropertyRepository
import javax.inject.Inject


class MazaadPropertyRepositoryImpl
@Inject constructor(
    private val api: MazaadApi,

    ) : MazaadPropertyRepository {
    override suspend fun getAllCategories(): AllCategories {

        return api.getAllCategoryData()
    }

    override suspend fun getSubCategoryProps(id: String): SubCategoryProps {
        return api.getSubCategoryProps(id)
    }
}