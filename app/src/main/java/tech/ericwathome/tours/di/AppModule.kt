package tech.ericwathome.tours.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.ericwathome.tours.repository.Repository
import tech.ericwathome.tours.repository.RepositoryImpl
import tech.ericwathome.tours.data.network.UnsplashApiService
import tech.ericwathome.tours.util.API_KEY
import tech.ericwathome.tours.util.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        val authInterceptor = Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("Authorization", "Client-ID $API_KEY")
                .build()
            chain.proceed(request)
        }
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUnsplashApiService(retrofit: Retrofit): UnsplashApiService {
        return retrofit.create(UnsplashApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(unsplashApiService: UnsplashApiService): Repository {
        return RepositoryImpl(unsplashApiService)
    }

}