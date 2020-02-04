package by.dz.remplace3.data.repository.auth

import by.dz.remplace3.data.model.User
import io.reactivex.Observable

interface IAuthRepository {

    fun login(login: String, password: String): Observable<User>

    fun getUser(): User
    fun isAuthorize(): Boolean

    fun clearUser()

}