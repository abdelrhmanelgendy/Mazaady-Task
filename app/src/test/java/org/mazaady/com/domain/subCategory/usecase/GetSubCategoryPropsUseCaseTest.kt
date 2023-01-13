package org.mazaady.com.domain.subCategory.usecase



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
import org.mazaady.com.data.child_options.api.ChildOptionsApi
import org.mazaady.com.data.child_options.dto.ChildOptions
import org.mazaady.com.data.child_options.repository.ChildOptionsRepositoryImpl
import org.mazaady.com.data.subCategory.api.SubCategoryApi
import org.mazaady.com.data.subCategory.dto.SubCategoryProps
import org.mazaady.com.data.subCategory.repository.SubCategoryRepositoryImpl
import org.mazaady.com.domain.category.repository.CategoryRepository
import org.mazaady.com.domain.child_options.repository.ChildOptionsRepository
import org.mazaady.com.domain.subCategory.repository.SubCategoryRepository
import org.mazaady.com.domain.util.DataState
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
internal class GetSubCategoryPropsUseCaseTest {

    private lateinit var subCategoryRepository: SubCategoryRepository

    @Mock
    private lateinit var api: SubCategoryApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        subCategoryRepository = SubCategoryRepositoryImpl(api)
    }

    @Test
    fun `test get sub category use case return successful options`() {
        runBlocking {

            val subCategoryPropsUseCase =  Mockito.mock(GetSubCategoryPropsUseCase::class.java)

            `when`(subCategoryPropsUseCase("-1"))
                .thenReturn(flow {
                    emit(DataState.Success(SubCategoryProps(code = -1)))
                })

            val result =subCategoryPropsUseCase("-1")
            result.collectLatest {resultData->
                flow { emit(DataState.Success(SubCategoryProps(code = -1))) }.collectLatest {categoriesData->
                    Assert.assertEquals(resultData.data,categoriesData.data)

                }

            }

        }
    }
}