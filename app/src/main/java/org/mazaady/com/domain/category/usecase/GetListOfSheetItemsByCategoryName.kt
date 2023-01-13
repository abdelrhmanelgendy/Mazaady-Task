package org.mazaady.com.domain.category.usecase

import org.mazaady.com.data.category.dto.AllCategories
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