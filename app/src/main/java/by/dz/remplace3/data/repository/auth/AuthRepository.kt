package by.dz.remplace3.data.repository.auth

import by.dz.remplace3.data.local.auth.IAuthLocalDataSource
import by.dz.remplace3.data.remote.auth.IAuthRemoteDataSource

class AuthRepository(
    private val remoteDataSource: IAuthRemoteDataSource,
    private val localDataSource: IAuthLocalDataSource
) : IAuthRepository {

    //TODO : Impl login logic
    override fun login(login: String, password: String) =
        remoteDataSource.login().flatMap {
            return@flatMap localDataSource.setUser(it)
        }!!

    override fun getUser() = localDataSource.getUserModel()

    override fun isAuthorize() = localDataSource.isAuthorize()

    override fun clearUser() {
        localDataSource.clearUser()
    }
}