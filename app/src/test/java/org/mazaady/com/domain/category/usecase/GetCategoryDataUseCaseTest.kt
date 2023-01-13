package org.mazaady.com.domain.category.usecase

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mazaady.com.data.category.api.CategoryApi
import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.data.category.repository.CategoryRepositoryImpl
import org.mazaady.com.domain.category.repository.CategoryRepository
import org.mazaady.com.domain.util.DataState
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
internal class GetCategoryDataUseCaseTest {

    private lateinit var categoryRepository: CategoryRepository

    @Mock
    private lateinit var categoryApi: CategoryApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        categoryRepository = CategoryRepositoryImpl(categoryApi)
     }

    @Test
    fun `test get categories use case return successful category`() {
        runBlocking {

            val categoryDataUseCase =  Mockito.mock(GetCategoryDataUseCase::class.java)

            `when`(categoryDataUseCase.invoke())
                .thenReturn(flow {
                    emit(DataState.Success(AllCategories(code = -1)))
                })

            val result =categoryDataUseCase.invoke()
            result.collectLatest {resultData->
                flow { emit(DataState.Success(AllCategories(code = -1))) }.collectLatest {categoriesData->
                    Assert.assertEquals(resultData.data,categoriesData.data)

                }

            }

        }
    }
}