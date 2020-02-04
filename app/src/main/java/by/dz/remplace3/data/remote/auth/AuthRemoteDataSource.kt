package by.dz.remplace3.data.remote.auth

class AuthRemoteDataSource(var authApi: AuthApi) : IAuthRemoteDataSource {

    override fun login() = authApi.login()

}