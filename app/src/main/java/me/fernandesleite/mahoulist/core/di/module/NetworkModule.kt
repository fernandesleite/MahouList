package me.fernandesleite.mahoulist.core.di.module

import android.content.Context
import android.util.Log
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import me.fernandesleite.mahoulist.BuildConfig
import me.fernandesleite.mahoulist.R
import me.fernandesleite.mahoulist.core.di.qualifier.AccessInterceptor
import me.fernandesleite.mahoulist.core.di.qualifier.NoTokenRefresh
import me.fernandesleite.mahoulist.core.di.qualifier.RefreshInterceptor
import me.fernandesleite.mahoulist.core.di.qualifier.TokenRefresh
import me.fernandesleite.mahoulist.feature.auth.domain.usecase.GetAccessTokenUseCase
import me.fernandesleite.mahoulist.feature.auth.domain.usecase.GetRefreshTokenUseCase
import me.fernandesleite.mahoulist.feature.auth.domain.usecase.SaveRefreshTokenUseCase
import me.fernandesleite.mahoulist.feature.auth.utils.AuthConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [ServiceModule::class])
@InstallIn(SingletonComponent::class)
class NetworkModule {
    companion object {
        private const val BASE_URL = "https://api.myanimelist.net/v2/"
    }

    // Retrofit
    @TokenRefresh
    @Provides
    fun provideMainRetrofit(moshi: Moshi, @TokenRefresh client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    @NoTokenRefresh
    @Provides
    fun provideAuthRetrofit(moshi: Moshi, @NoTokenRefresh client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AuthConstants.BASE_URL_AUTH)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    // Moshi
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    // OkHttp
    @TokenRefresh
    @Provides
    fun provideTokenRefreshOkhttp(
        @TokenRefresh loggingInterceptor: HttpLoggingInterceptor,
        @RefreshInterceptor refreshTokenInterceptor: Interceptor,
        @AccessInterceptor accessTokenInterceptor: Interceptor
    ): OkHttpClient {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(accessTokenInterceptor)
            .addInterceptor(refreshTokenInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @NoTokenRefresh
    @Provides
    fun provideAuthOkhttp(@NoTokenRefresh loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // Interceptors
    @RefreshInterceptor
    @Provides
    fun refreshTokenInterception(
        saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
        getRefreshTokenUseCase: GetRefreshTokenUseCase
    ): Interceptor {
        return Interceptor { chain ->
            chain.proceed(chain.request()).also { response ->
                if (response.code != 200) {
                    runBlocking {
                        getRefreshTokenUseCase.invoke().collect {
                            saveRefreshTokenUseCase.invoke(
                                BuildConfig.CLIENT_ID,
                                AuthConstants.REFRESH_TOKEN,
                                it
                            ).collect {
                            }
                        }
                    }
                    chain.proceed(chain.request())
                }
                response.code
            }

        }
    }

    @AccessInterceptor
    @Provides
    fun provideAccessTokenHeader(
        getAccessTokenUseCase: GetAccessTokenUseCase,
        @ApplicationContext applicationContext: Context
    ): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            if (request.header(AuthConstants.AUTHORIZATION_HEADER) != null) {
                return@Interceptor chain.proceed(request)
            }
            var authorizedRequest = request
            runBlocking {
                getAccessTokenUseCase.invoke().collect {
                    authorizedRequest = authorizedRequest
                        .newBuilder()
                        .header(
                            AuthConstants.AUTHORIZATION_HEADER,
                            applicationContext.getString(R.string.authorization_header, it)
                        )
                        .build()
                }
                chain.proceed(authorizedRequest)
            }

        }
    }

    // Log Interceptors
    @NoTokenRefresh
    @Provides
    fun provideLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            val logName = "NoTokenRefresh"
            try {
                val bufferedSource: Buffer = Buffer().writeUtf8(message)
                val readerValue = JsonReader.of(bufferedSource).readJsonValue()
                val adapter = Moshi.Builder().build().adapter(Any::class.java).indent("    ")
                val result = adapter.toJson(readerValue)
                Log.d(logName, result)
            } catch (m: Throwable) {
                Log.d(logName, message)
            }
        }
    }

    @TokenRefresh
    @Provides
    fun provideLoggingAuth(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            val logName = "TokenRefresh"
            try {
                val bufferedSource: Buffer = Buffer().writeUtf8(message)
                val readerValue = JsonReader.of(bufferedSource).readJsonValue()
                val adapter = Moshi.Builder().build().adapter(Any::class.java).indent("    ")
                val result = adapter.toJson(readerValue)
                Log.d(logName, result)
            } catch (m: Throwable) {
                Log.d(logName, message)
            }
        }
    }
}


