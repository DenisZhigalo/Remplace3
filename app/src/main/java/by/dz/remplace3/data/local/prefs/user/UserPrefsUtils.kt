package by.dz.remplace3.data.local.prefs.user

import android.content.SharedPreferences
import by.dz.remplace3.data.model.User
import com.google.gson.Gson
import io.reactivex.Observable

class UserPrefsUtils(private val pref: SharedPreferences) :
    IUserPrefsUtils {

    companion object {
        private const val PREFS_KEY_TOKEN = "user_token"
        private const val PREFS_KEY_USER = "user"
    }

    override fun saveToken(token: String) {
        pref.edit().putString(PREFS_KEY_TOKEN, token).apply()
    }

    override fun getToken() = pref.getString(PREFS_KEY_TOKEN, "") ?: ""

    override fun clearToken() {
        saveToken("")
    }

    override fun isEmptyToken() = getToken().isEmpty()

    override fun setUser(user: User): Observable<User> {
        pref.edit().putString(PREFS_KEY_USER, Gson().toJson(user)).apply()
        return Observable.just(user)
    }

    override fun getUser() = Observable.just(getUserModel())

    override fun getUserModel(): User {
        val userStr = pref.getString(PREFS_KEY_USER, "")
        var user = User()
        if (userStr.isNullOrEmpty()) {
            return user
        }
        try {
            user = Gson().fromJson(userStr, User::class.java)
        } catch (e: Exception) {
            //DO NOTHING
        }
        return user
    }

    override fun clearUser() {
        clearToken()
        pref.edit().putString(PREFS_KEY_USER, Gson().toJson(User())).apply()
    }

}