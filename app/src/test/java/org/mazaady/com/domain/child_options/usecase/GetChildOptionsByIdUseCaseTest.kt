package org.mazaady.com.domain.child_options.usecase



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
import org.mazaady.com.domain.category.repository.CategoryRepository
import org.mazaady.com.domain.child_options.repository.ChildOptionsRepository
import org.mazaady.com.domain.util.DataState
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
internal class GetChildOptionsByIdUseCaseTest {

    private lateinit var childOptionsRepository: ChildOptionsRepository

    @Mock
    private lateinit var api: ChildOptionsApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        childOptionsRepository = ChildOptionsRepositoryImpl(api)
    }

    @Test
    fun `test get child options use case return successful options`() {
        runBlocking {

            val childOptionsByIdUseCase =  Mockito.mock(GetChildOptionsByIdUseCase::class.java)

            `when`(childOptionsByIdUseCase.invoke("-1"))
                .thenReturn(flow {
                    emit(DataState.Success(ChildOptions(code = -1)))
                })

            val result =childOptionsByIdUseCase.invoke("-1")
            result.collectLatest {resultData->
                flow { emit(DataState.Success(ChildOptions(code = -1))) }.collectLatest {categoriesData->
                    Assert.assertEquals(resultData.data,categoriesData.data)

                }

            }

        }
    }
}