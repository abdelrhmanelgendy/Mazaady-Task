package org.mazaady.com.domain.usecase

import org.mazaady.com.data.network.dto.category_models.AllCategories

class GetListOfStringFromAllCategoriesUseCase {
    operator fun invoke(allCategories: AllCategories):List<String>
    {
       return allCategories.data?.categories?.map {
            it.name?:""
        }?: arrayListOf()
    }
}