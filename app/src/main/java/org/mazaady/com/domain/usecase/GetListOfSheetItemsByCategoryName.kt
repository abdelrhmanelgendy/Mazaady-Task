package org.mazaady.com.domain.usecase

import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryData
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetItem

class GetListOfSheetItemsByCategoryName {
    operator fun invoke(allCategories: AllCategories, selectedCategoryName: String):
            List<BottomSheetItem> {
        val selectedCategory = allCategories.data?.categories?.firstOrNull {
            it.name.equals(selectedCategoryName)
        }
        return  selectedCategory?.children?.map {
            BottomSheetItem(name = it.name.toString(), id = it.id!!, parent = -1,child = false,isOtherAdded = false)
        }?: arrayListOf()
    }
}