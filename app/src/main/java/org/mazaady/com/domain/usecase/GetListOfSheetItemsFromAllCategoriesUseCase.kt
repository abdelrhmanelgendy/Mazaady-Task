package org.mazaady.com.domain.usecase

import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetItem

class GetListOfSheetItemsFromAllCategoriesUseCase {
    operator fun invoke(allCategories: AllCategories):List<BottomSheetItem>
    {
       return allCategories.data?.categories?.map {
           BottomSheetItem(name = it.name.toString(), id = it.id!!, parent = -1, child = false,isOtherAdded = false)
        }?: arrayListOf()
    }
}