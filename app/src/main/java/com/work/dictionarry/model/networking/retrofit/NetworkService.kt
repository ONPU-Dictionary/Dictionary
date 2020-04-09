package com.work.dictionarry.model.networking.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor() {

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                Interceptor {
                    val original: Request = it.request()
                    val request = original.newBuilder()
                        .header("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                        .header(
                            "x-rapidapi-key",
                            "4e6812df4dmshf5856d1879d8f49p169d1bjsn01def3d96291"
                        )
                        .method(original.method(), original.body())
                        .build()
                    return@Interceptor it.proceed(
                        request
                    )
                }
            )
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val wordsApi: WordsApi = retrofit.create(WordsApi::class.java)

    companion object {
        private const val BASE_URL = "https://wordsapiv1.p.rapidapi.com/"
        private var instance: NetworkService? = null

        fun instance(): NetworkService {
            return instance
                ?: NetworkService()
        }
    }
}