package tech.ericwathome.tours.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
//    @Binds
//    @Singleton
//    abstract fun bindRepository(
//        repositoryImpl: RepositoryImpl
//    ): Repository
}