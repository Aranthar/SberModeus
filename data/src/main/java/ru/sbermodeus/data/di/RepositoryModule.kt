package ru.sbermodeus.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sbermodeus.data.datastore.remote.CourseDataStore
import ru.sbermodeus.data.datastore.remote.SpecializationDataStore
import ru.sbermodeus.data.datastore.remote.UserDataStore
import ru.sbermodeus.data.repository.CourseRepositoryImpl
import ru.sbermodeus.data.repository.SpecializationRepositoryImpl
import ru.sbermodeus.data.repository.UserRepositoryImpl
import ru.sbermodeus.domain.repository.CourseRepository
import ru.sbermodeus.domain.repository.SpecializationRepository
import ru.sbermodeus.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCourseRepository(courseDataStore: CourseDataStore): CourseRepository =
        CourseRepositoryImpl(courseDataStore)

    @Provides
    @Singleton
    fun provideUserRepository(userDataStore: UserDataStore): UserRepository =
        UserRepositoryImpl(userDataStore)

    @Provides
    @Singleton
    fun provideSpecializationRepository(specializationDataStore: SpecializationDataStore): SpecializationRepository =
        SpecializationRepositoryImpl(specializationDataStore)
}

