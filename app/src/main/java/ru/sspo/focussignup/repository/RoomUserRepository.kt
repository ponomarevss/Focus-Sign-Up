package ru.sspo.focussignup.repository

import ru.sspo.focussignup.domain.UserRepository
import ru.sspo.focussignup.repository.room.User
import ru.sspo.focussignup.repository.room.UserDao
import javax.inject.Inject

class RoomUserRepository @Inject constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    override suspend fun insertUser(username: String, email: String, password: String) {
        userDao.insert(User(username = username, email = email, password = password))
    }
}