package org.mazaady.com

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses
import org.mazaady.com.data.category.repository.CategoryRepositoryImplTest
import org.mazaady.com.data.child_options.repository.ChildOptionsRepositoryImplTest
import org.mazaady.com.data.subCategory.repository.SubCategoryRepositoryImplTest
import org.mazaady.com.domain.category.usecase.GetCategoryDataUseCase
import org.mazaady.com.domain.category.usecase.GetCategoryDataUseCaseTest
import org.mazaady.com.domain.child_options.usecase.GetChildOptionsByIdUseCaseTest
import org.mazaady.com.domain.subCategory.usecase.GetSubCategoryPropsUseCaseTest
import org.mazaady.com.domain.usecase.GetIdOfSubCategoryByNameTest
import org.mazaady.com.domain.usecase.GetListOfStringFromAllCategoriesUseCaseTest
import org.mazaady.com.presentation.bottom_sheet_dialog.adapter.NamesDiffUtilsTest
import org.mazaady.com.presentation.mazaad_result_viewer.adapter.MazaadDataViewerDiffUtilsTest

@RunWith(Suite::class)
@SuiteClasses(
    CategoryRepositoryImplTest::class,
    ChildOptionsRepositoryImplTest::class,
    SubCategoryRepositoryImplTest::class,
    GetCategoryDataUseCaseTest::class,
    GetChildOptionsByIdUseCaseTest::class,
    GetSubCategoryPropsUseCaseTest::class,
    GetIdOfSubCategoryByNameTest::class,
    GetListOfStringFromAllCategoriesUseCaseTest::class,
    NamesDiffUtilsTest::class,
    MazaadDataViewerDiffUtilsTest::class,


)
class TestsSuite {
}