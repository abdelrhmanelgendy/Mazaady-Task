package org.mazaady.com.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mazaady.com.data.network.MazaadApi
import org.mazaady.com.data.repository.MazaadPropertyRepositoryImpl
import org.mazaady.com.domain.repository.MazaadPropertyRepository
import org.mazaady.com.domain.usecase.GetCategoryDataUseCase
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
    fun provideMazaadApiService(retrofitBuilder: Retrofit.Builder): MazaadApi {
        return retrofitBuilder
            .build()
            .create(MazaadApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(
        api: MazaadApi,
    ) = MazaadPropertyRepositoryImpl(api) as MazaadPropertyRepository


    @Singleton
    @Provides
    fun provideGetCategoryUseCase(
        mazaadPropertyRepository: MazaadPropertyRepository
    ): GetCategoryDataUseCase{
        return GetCategoryDataUseCase(mazaadPropertyRepository)
    }







}



















