package org.mazaady.com.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.data.category.dto.Category
import org.mazaady.com.data.category.dto.Data

@RunWith(JUnit4::class)

class GetListOfStringFromAllCategoriesUseCaseTest {

    @Test
    fun `get list of category names with empty list return empty names`() {
        val allCategories = AllCategories(data = Data(categories = listOf()))
        val namesList =
            GetListOfSheetItemsFromAllCategoriesUseCase()(allCategories)
        assertEquals(namesList.size, 0)
    }

    @Test
    fun `get list of category names with null list return empty names`() {
        val allCategories = AllCategories(data = Data(categories = null))
        val namesList =
            GetListOfSheetItemsFromAllCategoriesUseCase()(allCategories)
        assertEquals(namesList.size, 0)
    }

    @Test
    fun `get list of category names with correct list return correct names`() {
        val allCategories =provideAllCategoryModel()
        val namesList =
            GetListOfSheetItemsFromAllCategoriesUseCase()(allCategories)
        assertEquals(namesList.size, allCategories.data?.categories?.size)
    }
    private fun provideAllCategoryModel(): AllCategories {
        return AllCategories(
            data = Data(
                categories = listOf(
                    Category(name = "CARS , MOTORCYCLES & ACCESSORIES"),
                    Category(name = "FURNITURE & FURNISHINGS FITTINGS"),
                    Category(name = "ELECTRICAL, MOBILES & ACCESSORIES"),
                    Category(name = "REAL ESTATE , Trade Names"),
                )
            )
        )
    }
}