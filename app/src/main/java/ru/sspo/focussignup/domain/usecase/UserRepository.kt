package ru.sspo.focussignup.domain.usecase

import ru.sspo.focussignup.domain.repository.room.User

interface UserRepository {
    suspend fun getUserByEmail(email: String): User?

    suspend fun insertUser(username: String, email: String, password: String)
}