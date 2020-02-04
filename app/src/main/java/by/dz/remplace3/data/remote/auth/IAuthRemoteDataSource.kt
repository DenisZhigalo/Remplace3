package by.dz.remplace3.data.remote.auth

import by.dz.remplace3.data.model.User
import io.reactivex.Observable

interface IAuthRemoteDataSource {

    fun login(): Observable<User>

}