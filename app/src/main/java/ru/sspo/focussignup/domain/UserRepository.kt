package ru.sspo.focussignup.domain

import ru.sspo.focussignup.repository.room.User

interface UserRepository {
    suspend fun getUserByEmail(email: String): User?

    suspend fun insertUser(username: String, email: String, password: String)
}