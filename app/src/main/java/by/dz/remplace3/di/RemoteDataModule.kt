package by.dz.remplace3.di

import by.dz.remplace3.data.remote.auth.AuthRemoteDataSource
import by.dz.remplace3.data.remote.auth.IAuthRemoteDataSource
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

private const val MODULE_NAME = "RemoteData Module"

val remoteModule = Kodein.Module(MODULE_NAME, false) {
    bind<IAuthRemoteDataSource>() with singleton { AuthRemoteDataSource(instance()) }

}