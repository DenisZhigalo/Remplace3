package by.dz.remplace3.di

import by.dz.remplace3.data.local.auth.AuthLocalDataSource
import by.dz.remplace3.data.local.auth.IAuthLocalDataSource
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

private const val MODULE_NAME = "LocalData Module"

val localModule = Kodein.Module(MODULE_NAME, false) {
    importOnce(appModule)
    bind<IAuthLocalDataSource>() with singleton { AuthLocalDataSource(instance()) }
}