package by.dz.remplace3

import android.app.Application
import android.content.Context
import by.dz.remplace3.di.appModule
import by.dz.remplace3.di.networkModule
import by.dz.remplace3.di.repositoryModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import timber.log.Timber

class CustomApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind<Context>("ApplicationContext") with singleton { this@CustomApplication.applicationContext }
        bind<CustomApplication>() with singleton { this@CustomApplication }
        import(appModule)
        import(networkModule)
        import(repositoryModule)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}