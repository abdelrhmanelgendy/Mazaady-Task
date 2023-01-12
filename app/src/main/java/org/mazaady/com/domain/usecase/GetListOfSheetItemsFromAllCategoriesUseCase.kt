package org.mazaady.com.domain.usecase

import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryData
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetItem

class GetListOfSheetItemsFromAllCategoriesUseCase {
    operator fun invoke(allCategories: AllCategories):List<BottomSheetItem>
    {
       return allCategories.data?.categories?.map {
           BottomSheetItem(name = it.name.toString(), id = it.id!!, parent = -1, child = false,isOtherAdded = false)
        }?: arrayListOf()
    }
}