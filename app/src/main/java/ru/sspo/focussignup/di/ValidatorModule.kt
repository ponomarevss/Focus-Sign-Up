package ru.sspo.focussignup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sspo.focussignup.domain.usecase.SignUpValidator
import ru.sspo.focussignup.domain.validator.SignUpValidatorImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ValidatorModule {

    @Binds
    abstract fun bindValidator(impl: SignUpValidatorImpl): SignUpValidator
}