package org.mazaady.com.domain.usecase

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.data.category.dto.Category
import org.mazaady.com.data.category.dto.Children
import org.mazaady.com.data.category.dto.Data
import org.mazaady.com.domain.category.usecase.GetListOfSheetItemsByCategoryName

@RunWith(JUnit4::class)
class GetIdOfSubCategoryByNameTest {

    @Test
    fun `get list of sub category names with empty category name return empty names`() {
        val allCategories = provideAllCategoryModel()
        val subCategoryNamesList =
            GetListOfSheetItemsByCategoryName()(allCategories, "")
        Assert.assertEquals(subCategoryNamesList.size, 0)
    }

    @Test
    fun `get list of sub category names with empty category list return empty names`() {
        val allCategories = AllCategories(data = Data(categories = arrayListOf()))
        val subCategoryNamesList =
            GetListOfSheetItemsByCategoryName()(allCategories, "CARS , MOTORCYCLES & ACCESSORIES")
        Assert.assertEquals(subCategoryNamesList.size, 0)
    }

    @Test
    fun `get list of sub category names with null category list return empty names`() {
        val allCategories = AllCategories(data = Data(categories = null))
        val subCategoryNamesList =
            GetListOfSheetItemsByCategoryName()(allCategories, "CARS , MOTORCYCLES & ACCESSORIES")
        Assert.assertEquals(subCategoryNamesList.size, 0)
    }

    @Test
    fun `get list of sub category names with correct category list return correct names`() {
        val allCategories = provideAllCategoryModel()
        val subCategoryNamesList =
            GetListOfSheetItemsByCategoryName()(allCategories, "CARS , MOTORCYCLES & ACCESSORIES")
        Assert.assertEquals(subCategoryNamesList.size, 4)
    }




    private fun provideAllCategoryModel(): AllCategories {
        return AllCategories(
            data = Data(
                categories = listOf(
                    Category(name = "CARS , MOTORCYCLES & ACCESSORIES", id = -1,
                        children = listOf(
                            Children(name = "Cars", id = -1),
                            Children(name = "Auto Parts & Accessories", id = -1),
                            Children(name = "Motorcycles", id = -1),
                            Children(name = "Bicycles", id = -1),
                        )),
                    Category(name = "FURNITURE & FURNISHINGS FITTINGS", id = -1),

                )
            )
        )
    }
}