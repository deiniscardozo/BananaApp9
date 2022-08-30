package com.overcom.bananaapp9.data.model.repositories

import com.overcom.bananaapp9.core.ApiAdapter
import com.overcom.bananaapp9.data.network.ApiService

class ThirdsDetailRepository {

    private val apiService : ApiService = ApiAdapter.getApiAdapter().create(ApiService::class.java)

    suspend fun callThirdsDetail(id: String) =
        apiService.listThirdsDetail(id,"1")
}