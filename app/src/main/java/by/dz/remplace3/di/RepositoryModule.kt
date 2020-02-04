package by.dz.remplace3.di

import by.dz.remplace3.data.repository.auth.AuthRepository
import by.dz.remplace3.data.repository.auth.IAuthRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

private const val MODULE_NAME = "Repository Module"

val repositoryModule = Kodein.Module(MODULE_NAME, false) {
    import(remoteModule)
    import(localModule)
    bind<IAuthRepository>() with singleton { AuthRepository(instance(), instance()) }
}