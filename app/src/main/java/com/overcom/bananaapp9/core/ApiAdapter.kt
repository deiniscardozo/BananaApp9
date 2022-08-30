package com.overcom.bananaapp9.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiAdapter {

    companion object{

        private const val baseUrl = "https://server.bananaerp.com/"
        //private const val baseUrl = "https://devserver.bananaerp.com/"
        fun getApiAdapter(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()

        }

        private fun getClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(ApiInterceptor())
                .build()

        }
    }
}

