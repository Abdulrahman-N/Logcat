package app.assignment.logcat.di

import app.assignment.logcat.data.NewsDataSource
import app.assignment.logcat.data.NewsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindNewsDataSource(
        newsDataSourceImpl: NewsDataSourceImpl
    ): NewsDataSource

}