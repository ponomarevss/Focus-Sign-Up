package ru.sspo.focussignup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sspo.focussignup.repository.RoomUserRepository
import ru.sspo.focussignup.domain.UserRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(impl: RoomUserRepository): UserRepository
}