package by.dz.remplace3.data.local.prefs.user

import by.dz.remplace3.data.model.User
import io.reactivex.Observable

interface IUserPrefsUtils {

    fun saveToken(token: String)

    fun getToken(): String

    fun clearToken()

    fun setUser(user: User): Observable<User>

    fun getUser(): Observable<User>

    fun getUserModel(): User

    fun clearUser()

    fun isEmptyToken(): Boolean

}