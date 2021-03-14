package com.yureka.technology.ytc.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.yureka.technology.ytc.BuildConfig
import com.yureka.technology.ytc.data.local.LocalDataSource
import com.yureka.technology.ytc.data.local.SharedPreferenceHelper
import com.yureka.technology.ytc.data.remote.RemoteDataSource
import com.yureka.technology.ytc.data.remote.api.CourseServiceApi
import com.yureka.technology.ytc.data.remote.api.QuestionServiceApi
import com.yureka.technology.ytc.data.remote.api.WebServiceApi
import com.yureka.technology.ytc.data.remote.interceptor.AuthInterceptor
import com.yureka.technology.ytc.data.remote.interceptor.IdInterceptor
import com.yureka.technology.ytc.data.remote.interceptor.JsonInterceptor
import com.yureka.technology.ytc.data.remote.interceptor.MockRequestInterceptor
import com.yureka.technology.ytc.data.repository.AppRepository
import com.yureka.technology.ytc.data.repository.CourseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(
            if (BuildConfig.DEBUG) "http://35.219.4.100:8000/course/v1/" else "http://35.219.4.100:8000/course/v1/"
        )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    fun provideWebServiceApi(retrofit: Retrofit): WebServiceApi =
        retrofit.create(WebServiceApi::class.java)

    @Provides
    fun provideMateriServiceApi(retrofit: Retrofit): CourseServiceApi =
        retrofit.create(CourseServiceApi::class.java)

    @Provides
    fun provideQuestionServiceApi(retrofit: Retrofit): QuestionServiceApi =
        retrofit.create(QuestionServiceApi::class.java)

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        jsonInterceptor: JsonInterceptor,
        mockRequestInterceptor: MockRequestInterceptor,
        idInterceptor: IdInterceptor
    ): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addNetworkInterceptor(jsonInterceptor)
            .addInterceptor(idInterceptor)
        if (BuildConfig.DEBUG) {
            builder
                .addInterceptor(mockRequestInterceptor)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
        }
        return builder.build()
    }

    @Provides
    fun provideAuthInterceptor(sessionHelper: SharedPreferenceHelper): AuthInterceptor =
        AuthInterceptor(sessionHelper)

    @Provides
    fun provideJsonInterceptor(): JsonInterceptor = JsonInterceptor()

    @Provides
    fun provideIdInterceptor(): IdInterceptor = IdInterceptor()

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        webServiceApi: WebServiceApi,
        courseServiceApi: CourseServiceApi,
        questionServiceApi: QuestionServiceApi
    ): RemoteDataSource = RemoteDataSource(webServiceApi, courseServiceApi, questionServiceApi)

//    Local

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("yureka-session", 0)

    @Singleton
    @Provides
    fun providePreferenceHelper(
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): SharedPreferenceHelper = SharedPreferenceHelper(sharedPreferences, gson)

    @Singleton
    @Provides
    fun provideLocalDataSource(helper: SharedPreferenceHelper): LocalDataSource =
        LocalDataSource(helper)


    //repository

    @Singleton
    @Provides
    fun provideAppRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): AppRepository = AppRepository(localDataSource, remoteDataSource)

    @Singleton
    @Provides
    fun provideCourseRepository(
        remoteDataSource: RemoteDataSource
    ): CourseRepository = CourseRepository(remoteDataSource)

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext app: Context) = app
}
