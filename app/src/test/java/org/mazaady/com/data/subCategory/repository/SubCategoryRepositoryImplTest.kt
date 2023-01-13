package org.mazaady.com.data.subCategory.repository


import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mazaady.com.data.subCategory.api.SubCategoryApi
import org.mazaady.com.data.subCategory.dto.SubCategoryProps
import org.mazaady.com.domain.subCategory.repository.SubCategoryRepository
import org.mazaady.com.domain.util.DataState
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
internal class SubCategoryRepositoryImplTest {
    private lateinit var subCategoryRepository: SubCategoryRepository

    @Mock
    private lateinit var subCategoryApi: SubCategoryApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        subCategoryRepository = SubCategoryRepositoryImpl(subCategoryApi)
    }

    @Test
    fun `test get all sub categories return a successful result of all sub category`() {
        runBlocking {

            Mockito.`when`(subCategoryRepository.getSubCategoryProps("-1"))
                .thenReturn(
                    SubCategoryProps(code = -1, data = null, msg = "-1")
                )

            val result = subCategoryRepository.getSubCategoryProps("-1")

            Assert.assertEquals(
                result, DataState.Success(
                    SubCategoryProps(code = -1, data = null, msg = "-1")
                ).data
            )
        }
    }

}