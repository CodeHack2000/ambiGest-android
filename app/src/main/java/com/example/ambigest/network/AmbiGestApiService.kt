package com.example.ambigest.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "http://localhost:3000/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AmbiGestApiService {
    @POST("auth/login")
    suspend fun postLogin(
        @Body request: LoginRequest
    ): Response<ResponseBody>
}

object AmbiGestApi {
    val retrofitService: AmbiGestApiService by lazy {
        retrofit.create(AmbiGestApiService::class.java)
    }
}