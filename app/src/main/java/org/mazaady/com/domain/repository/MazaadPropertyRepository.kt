package org.mazaady.com.domain.repository

import org.mazaady.com.data.network.dto.models.AllCategories

interface MazaadPropertyRepository {

    suspend  fun getAllCategories(): AllCategories
 }