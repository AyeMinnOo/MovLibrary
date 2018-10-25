package com.amo.movlibrary.di


import android.content.Context
import com.amo.movlibrary.app.App
import com.amo.movlibrary.appconstants.ApiEndpoint.BASE_URL
import com.amo.movlibrary.appconstants.AppConstants.IMDB_API_KEY
import com.amo.movlibrary.data.AppDataManager
import com.amo.movlibrary.data.DataManager
import com.amo.movlibrary.data.pref.AppPrefHelper
import com.amo.movlibrary.data.pref.PrefHelper
import com.amo.movlibrary.data.remote.ApiHelper
import com.amo.movlibrary.utils.NetworkUtil
import com.amo.movlibrary.utils.NetworkUtilHelper
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule() {

    @Provides
    @Singleton
    fun provideApplication(app: App): Context = app

    @PrefInfo
    @Provides
    fun providePrefName() = "PREF_NAME"

    @DbInfo
    @Provides
    fun provideDbName() = "MOVLIBRARY_DB"

    @Singleton
    @Provides
    fun providePref(appPrefHelper: AppPrefHelper): PrefHelper = appPrefHelper

    @Singleton
    @Provides
    fun provideNetworkUtil(networkUtilHelper: NetworkUtilHelper): NetworkUtil = networkUtilHelper

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        val fieldInterceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url()

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", IMDB_API_KEY)
                    .build()

                val request = original.newBuilder().url(url).build()
                return chain.proceed(request)
            }
        }
        builder.addInterceptor(fieldInterceptor)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideApiHelper(client: OkHttpClient): ApiHelper {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiHelper::class.java)
    }

    @Singleton
    @Provides
    fun provideDataManger(appDataManager: AppDataManager): DataManager = appDataManager

}