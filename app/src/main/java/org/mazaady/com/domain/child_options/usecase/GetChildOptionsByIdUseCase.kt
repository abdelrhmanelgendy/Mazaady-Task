package org.mazaady.com.domain.child_options.usecase

import android.util.Log
import org.mazaady.com.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mazaady.com.data.child_options.dto.ChildOptions
import org.mazaady.com.domain.child_options.repository.ChildOptionsRepository
 import java.lang.Exception
import javax.inject.Inject

open class GetChildOptionsByIdUseCase
@Inject
constructor(
    private val childOptionsRepository: ChildOptionsRepository
    ) {

    operator open  fun invoke(id:String): Flow<DataState<ChildOptions>>
    = flow {
        try {
            emit(DataState.Loading())
            val options = childOptionsRepository.getChildOptions(id)
            emit(DataState.Success(options))
        } catch (e: Exception) {
            emit(DataState.Error("${e.message}"))

        }
    }
}