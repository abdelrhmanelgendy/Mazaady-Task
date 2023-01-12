package org.mazaady.com.domain.usecase

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.data.network.entity.category_models.Category
import org.mazaady.com.data.network.entity.category_models.Children
import org.mazaady.com.data.network.entity.category_models.Data

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
                    Category(name = "CARS , MOTORCYCLES & ACCESSORIES",
                        children = listOf(
                            Children(name = "Cars"),
                            Children(name = "Auto Parts & Accessories"),
                            Children(name = "Motorcycles"),
                            Children(name = "Bicycles"),
                        )),
                    Category(name = "FURNITURE & FURNISHINGS FITTINGS"),

                )
            )
        )
    }
}