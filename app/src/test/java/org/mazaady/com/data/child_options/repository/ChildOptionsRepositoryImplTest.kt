package org.mazaady.com.data.child_options.repository

 import org.mazaady.com.data.category.repository.CategoryRepositoryImpl

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mazaady.com.data.category.api.CategoryApi
import org.mazaady.com.data.category.dto.AllCategories
 import org.mazaady.com.data.child_options.api.ChildOptionsApi
 import org.mazaady.com.data.child_options.dto.ChildOptions
 import org.mazaady.com.domain.category.repository.CategoryRepository
 import org.mazaady.com.domain.child_options.repository.ChildOptionsRepository
 import org.mazaady.com.domain.util.DataState
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
internal class ChildOptionsRepositoryImplTest{
    private lateinit var childOptionsRepository: ChildOptionsRepository

    @Mock
    private lateinit var childOptionsApi: ChildOptionsApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        childOptionsRepository = ChildOptionsRepositoryImpl(childOptionsApi)
    }

    @Test
    fun `test get all categories return a successful result of all category`(){
        runBlocking {

            Mockito.`when`(childOptionsRepository.getChildOptions("-1"))
                .thenReturn(
                    ChildOptions(code = -1, data = null, msg = "-1")
                )

            val result = childOptionsRepository.getChildOptions("-1")

            Assert.assertEquals(result, DataState.Success( ChildOptions(code = -1, data = null, msg = "-1")).data)
        }
    }

}