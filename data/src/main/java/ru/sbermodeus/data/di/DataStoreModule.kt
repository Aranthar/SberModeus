package ru.sbermodeus.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sbermodeus.data.datastore.remote.CourseDataStore
import ru.sbermodeus.data.datastore.remote.SpecializationDataStore
import ru.sbermodeus.data.datastore.remote.UserDataStore
import ru.sbermodeus.data.ktor.RequestManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideUserDataStore(requestManager: RequestManager): UserDataStore =
        UserDataStore(requestManager)

    @Provides
    @Singleton
    fun provideCourseDataStore(requestManager: RequestManager): CourseDataStore =
        CourseDataStore(requestManager)

    @Provides
    @Singleton
    fun provideSpecializationDataStore(requestManager: RequestManager): SpecializationDataStore =
        SpecializationDataStore(requestManager)
}