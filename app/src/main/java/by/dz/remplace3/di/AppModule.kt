package by.dz.remplace3.di

import android.content.Context
import android.content.res.Resources
import by.dz.remplace3.CustomApplication
import by.dz.remplace3.data.local.prefs.user.IUserPrefsUtils
import by.dz.remplace3.data.local.prefs.user.UserPrefsUtils
import by.dz.remplace3.utils.INetManager
import by.dz.remplace3.utils.IRxSchedulers
import by.dz.remplace3.utils.NetManager
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

private const val MODULE_NAME = "App Module"
private const val USER_PREF_NAME = "user_pref"
private const val PRIVATE_MODE = 0

val appModule = Module(MODULE_NAME, false) {
    bind<Resources>() with singleton { instance<CustomApplication>().resources }
    bind<IRxSchedulers>() with singleton { getIRxSchedulers() }
    bind<IUserPrefsUtils>() with singleton { getUserPrefsUtils(instance("ApplicationContext")) }
    bind<INetManager>() with singleton { getINetManager(instance("ApplicationContext")) }
}

private fun getIRxSchedulers(): IRxSchedulers = object : IRxSchedulers {
    override fun main(): Scheduler = AndroidSchedulers.mainThread()
    override fun io(): Scheduler = Schedulers.io()
}

private fun getUserPrefsUtils(context: Context) =
    UserPrefsUtils(
        context.getSharedPreferences(
            USER_PREF_NAME,
            PRIVATE_MODE
        )
    )

private fun getINetManager(context: Context) = NetManager(context)