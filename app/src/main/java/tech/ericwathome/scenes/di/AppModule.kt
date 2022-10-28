package tech.ericwathome.scenes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.ericwathome.scenes.feature_scenes.data.data_source.local.PhotoDao
import tech.ericwathome.scenes.feature_scenes.data.data_source.local.PhotoDatabase
import tech.ericwathome.scenes.feature_scenes.data.data_source.remote.UnsplashApiService
import tech.ericwathome.scenes.feature_scenes.domain.repository.Repository
import tech.ericwathome.scenes.feature_scenes.domain.use_case.AddBookmarks
import tech.ericwathome.scenes.feature_scenes.domain.use_case.AllPhotos
import tech.ericwathome.scenes.feature_scenes.domain.use_case.BookmarkedPhotos
import tech.ericwathome.scenes.feature_scenes.domain.use_case.DeletePhoto
import tech.ericwathome.scenes.feature_scenes.domain.util.ScenesUseCases
import tech.ericwathome.scenes.util.API_KEY
import tech.ericwathome.scenes.util.BASE_URL
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
    fun provideAppDatabase(@ApplicationContext context: Context): PhotoDatabase {
        return Room.databaseBuilder(
            context,
            PhotoDatabase::class.java,
            "photo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePhotoDao(photoDatabase: PhotoDatabase): PhotoDao {
        return photoDatabase.photoDao()
    }

    @Provides
    @Singleton
    fun provideScenesUseCases(repository: Repository): ScenesUseCases {
        return ScenesUseCases(
            addBookmarks = AddBookmarks(repository),
            allPhotos = AllPhotos(repository),
            bookmarkedPhotos = BookmarkedPhotos(repository),
            deletePhoto = DeletePhoto(repository)
        )
    }

}