package tech.ericwathome.tours.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.ericwathome.tours.data.Repository
import tech.ericwathome.tours.data.RepositoryImpl
import tech.ericwathome.tours.data.network.UnsplashApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}