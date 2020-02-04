package by.dz.remplace3.data.remote.auth

import by.dz.remplace3.data.model.User
import by.dz.remplace3.di.NO_AUTH_HEADER_KEY_TRUE
import io.reactivex.Observable
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/sign-in")
    @Headers(NO_AUTH_HEADER_KEY_TRUE)
    fun login(): Observable<User>

}