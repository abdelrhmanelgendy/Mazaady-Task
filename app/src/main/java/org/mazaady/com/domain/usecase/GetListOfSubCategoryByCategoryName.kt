package org.mazaady.com.domain.usecase

import org.mazaady.com.data.network.dto.category_models.AllCategories

class GetListOfSubCategoryByCategoryName {
    operator fun invoke(allCategories: AllCategories, selectedCategoryName: String):
            List<String> {
        val selectedCategory = allCategories.data?.categories?.firstOrNull {
            it.name.equals(selectedCategoryName)
        }
        return  selectedCategory?.children?.map {
            it.name?:""
        }?: arrayListOf()
    }
}