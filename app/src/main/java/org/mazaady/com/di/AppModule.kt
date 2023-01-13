package org.mazaady.com.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mazaady.com.data.category.api.CategoryApi
import org.mazaady.com.data.category.repository.CategoryRepositoryImpl
import org.mazaady.com.data.child_options.api.ChildOptionsApi
import org.mazaady.com.data.child_options.repository.ChildOptionsRepositoryImpl
import org.mazaady.com.data.subCategory.api.SubCategoryApi
import org.mazaady.com.data.subCategory.repository.SubCategoryRepositoryImpl
import org.mazaady.com.domain.category.repository.CategoryRepository
import org.mazaady.com.domain.category.usecase.GetCategoryDataUseCase
import org.mazaady.com.domain.child_options.repository.ChildOptionsRepository
import org.mazaady.com.domain.child_options.usecase.GetChildOptionsByIdUseCase
import org.mazaady.com.domain.subCategory.repository.SubCategoryRepository
import org.mazaady.com.domain.subCategory.usecase.GetSubCategoryPropsUseCase
import org.mazaady.com.utils.End_points
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(End_points.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }


    @Singleton
    @Provides
    fun provideCategoryApiService(retrofitBuilder: Retrofit.Builder): CategoryApi {
        return retrofitBuilder
            .build()
            .create(CategoryApi::class.java)
    }
    @Singleton
    @Provides
    fun provideSubCategoryApiService(retrofitBuilder: Retrofit.Builder): SubCategoryApi {
        return retrofitBuilder
            .build()
            .create(SubCategoryApi::class.java)
    }

    @Singleton
    @Provides
    fun provideChildOptionsApiService(retrofitBuilder: Retrofit.Builder): ChildOptionsApi {
        return retrofitBuilder
            .build()
            .create(ChildOptionsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(
        api: CategoryApi,
    ) = CategoryRepositoryImpl(api) as CategoryRepository

    @Singleton
    @Provides
    fun provideSubCategoryRepository(
        api: SubCategoryApi,
    ) = SubCategoryRepositoryImpl(api) as SubCategoryRepository

    @Singleton
    @Provides
    fun provideChildOptionsRepository(
        api: ChildOptionsApi,
    ) = ChildOptionsRepositoryImpl(api) as ChildOptionsRepository

//
    @Singleton
    @Provides
    fun provideGetCategoryUseCase(
        repository: CategoryRepository
    ): GetCategoryDataUseCase {
        return GetCategoryDataUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetSubCategoryUseCase(
        repository: SubCategoryRepository
    ): GetSubCategoryPropsUseCase {
        return GetSubCategoryPropsUseCase(repository)
    }


    @Singleton
    @Provides
    fun provideGetChildOptionsUseCase(
        repository: ChildOptionsRepository
    ): GetChildOptionsByIdUseCase {
        return GetChildOptionsByIdUseCase(repository)
    }








}



















