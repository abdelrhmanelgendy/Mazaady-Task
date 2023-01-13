package org.mazaady.com.data.category.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mazaady.com.data.category.api.CategoryApi
import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.domain.category.repository.CategoryRepository
import org.mazaady.com.domain.util.DataState
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
internal class CategoryRepositoryImplTest{
    private lateinit var categoryRepository: CategoryRepository

    @Mock
    private lateinit var categoryApi: CategoryApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        categoryRepository = CategoryRepositoryImpl(categoryApi)
    }

    @Test
    fun `test get all categories return a successful result of all category`(){
        runBlocking {

            Mockito.`when`(categoryRepository.getAllCategories())
                .thenReturn(
                    AllCategories(code = -1)
                )

            val result = categoryRepository.getAllCategories()

            Assert.assertEquals(result, DataState.Success(AllCategories(code = -1)).data)
        }
    }

}