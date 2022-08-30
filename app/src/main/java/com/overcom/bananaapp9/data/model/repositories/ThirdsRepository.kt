package com.overcom.bananaapp9.data.model.repositories

import com.overcom.bananaapp9.core.ApiAdapter
import com.overcom.bananaapp9.data.network.ApiService

class ThirdsRepository {

    private val apiService : ApiService = ApiAdapter.getApiAdapter().create(ApiService::class.java)

    suspend fun thirdsCall(type_third: String, position: String,
                           limit: String, filter: String) =
        apiService.listThirds(type_third, position, limit, filter)
}