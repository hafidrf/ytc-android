package com.yureka.technology.ytc.di

import com.yureka.technology.ytc.BuildConfig
import com.yureka.technology.ytc.api.WebServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {
/*    @Provides
    @Singleton
    internal fun okHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            // Only add HTTP logs for debug builds
            builder.addInterceptor(logging)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideWebServiceApi(client: OkHttpClient): WebServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://devel-mock.web.app")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(WebServiceApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideMateriServiceApi(client: OkHttpClient): MateriServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.101.181.128:8000/course/")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MateriServiceApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideactivityServiceApi(client: OkHttpClient): ActivityServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.101.181.128:8000/course/")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ActivityServiceApi::class.java)
    }*/
}
