package org.mazaady.com.data.child_options.repository


import org.mazaady.com.data.child_options.api.ChildOptionsApi
 import org.mazaady.com.data.child_options.dto.ChildOptions
import org.mazaady.com.domain.child_options.repository.ChildOptionsRepository
import javax.inject.Inject


class ChildOptionsRepositoryImpl
@Inject constructor(
    private val api: ChildOptionsApi,

    ) : ChildOptionsRepository {
    override suspend fun getChildOptions(id: String): ChildOptions {
      return  api.getChildOptions(id);

    }
}