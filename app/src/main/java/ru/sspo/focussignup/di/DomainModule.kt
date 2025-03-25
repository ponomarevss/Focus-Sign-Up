package ru.sspo.focussignup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sspo.focussignup.ui.viewmodel.SignUpUseCase
import ru.sspo.focussignup.domain.SignUpUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase
}