package ru.sspo.focussignup.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.sspo.focussignup.domain.SignUpUseCase
import ru.sspo.focussignup.repository.RoomUserRepository
import ru.sspo.focussignup.repository.UserRepository
import ru.sspo.focussignup.repository.room.AppDatabase
import ru.sspo.focussignup.repository.room.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository = RoomUserRepository(userDao)

    @Provides
    @Singleton
    fun provideSignUpUseCase(): SignUpUseCase = SignUpUseCase()
}