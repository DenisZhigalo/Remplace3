package by.dz.remplace3.data.local.auth

import by.dz.remplace3.data.local.prefs.user.IUserPrefsUtils
import by.dz.remplace3.data.model.User

class AuthLocalDataSource(private var userPrefs: IUserPrefsUtils) : IAuthLocalDataSource {

    override fun isAuthorize() = !userPrefs.isEmptyToken()

    override fun setUser(user: User) = userPrefs.setUser(user)

    override fun getUser() = userPrefs.getUser()

    override fun getUserModel() = userPrefs.getUserModel()

    override fun getUserId() = getUserModel().id

    override fun getUsername() = getUserModel().userName

    override fun clearUser() {
        userPrefs.clearUser()
    }

}