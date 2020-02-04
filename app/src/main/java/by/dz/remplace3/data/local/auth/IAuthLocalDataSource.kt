package by.dz.remplace3.data.local.auth

import by.dz.remplace3.data.model.User
import io.reactivex.Observable

interface IAuthLocalDataSource {

    fun setUser(user: User): Observable<User>

    fun getUser(): Observable<User>

    fun getUserModel(): User

    fun getUserId(): String

    fun getUsername(): String

    fun clearUser()

    fun isAuthorize(): Boolean

}