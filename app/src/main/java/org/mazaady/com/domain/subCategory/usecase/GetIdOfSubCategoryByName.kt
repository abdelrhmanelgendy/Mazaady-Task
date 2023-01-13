package org.mazaady.com.domain.subCategory.usecase

import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.data.category.dto.Children

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