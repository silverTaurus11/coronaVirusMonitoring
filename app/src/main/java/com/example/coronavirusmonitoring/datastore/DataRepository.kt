package com.example.coronavirusmonitoring.datastore

import android.content.Context
import com.example.coronavirusmonitoring.R
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import okhttp3.logging.HttpLoggingInterceptor

object DataRepository {

    fun create(baseUrl: String, client: OkHttpClient): Service {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()


        return retrofit.create(Service::class.java)
    }

    fun createCovidAPI(context: Context): Service {
        val baseUrl = context.resources.getString(R.string.covid_19_url)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(
                object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request = chain.request().newBuilder()
                            .addHeader("Accept", "Application/JSON")
                            .addHeader("x-rapidapi-host", context.resources.getString(R.string.covid_19_host))
                            .addHeader("x-rapidapi-key", context.resources.getString(R.string.covid_19_key))
                            .build()
                        return chain.proceed(request)
                    }
                })
            .addInterceptor(logging).build()

        return create(
            baseUrl,
            client
        )
    }
}
