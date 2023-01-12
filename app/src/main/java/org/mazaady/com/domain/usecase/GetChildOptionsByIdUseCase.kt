package org.mazaady.com.domain.usecase

import android.util.Log
import org.mazaady.com.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryProps
import org.mazaady.com.data.network.entity.child_options_model.ChildOptions
import org.mazaady.com.domain.repository.MazaadPropertyRepository
import java.lang.Exception
import javax.inject.Inject

class GetChildOptionsByIdUseCase
@Inject
constructor(
    private val mazaadPropertyRepository: MazaadPropertyRepository
    ) {

    operator fun invoke(id:String): Flow<DataState<ChildOptions>>
    = flow {
        try {
            emit(DataState.Loading())
            val options = mazaadPropertyRepository.getChildOptions(id)
            emit(DataState.Success(options))
        } catch (e: Exception) {
            Log.d("TAG", "invoke: "+e.toString())
            emit(DataState.Error("${e.message}"))
//            emit(DataState.Error("Check your internet connection!"))
        }
    }
}