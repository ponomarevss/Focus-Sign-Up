package ru.sspo.focussignup.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.sspo.focussignup.domain.usecase.StringProvider
import ru.sspo.focussignup.resources.StringProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourcesModule {

    @Provides
    @Singleton
    fun provideResources(@ApplicationContext appContext: Context): StringProvider =
        StringProviderImpl(appContext)
}