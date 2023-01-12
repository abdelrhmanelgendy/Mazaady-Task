package org.mazaady.com.domain.usecase

import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.data.network.entity.category_models.Children

class GetIdOfSubCategoryByName {
    operator fun invoke(allCategories: AllCategories, selectedCategoryName: String, selectedSubCategoryName: String):
           Children? {
        val selectedCategory = allCategories.data?.categories?.firstOrNull() {
            it.name.equals(selectedCategoryName)
        }
        return  selectedCategory?.children?.firstOrNull {
            it.name?.toLowerCase().equals(selectedSubCategoryName.toLowerCase())
        }
    }
}