package by.dz.remplace3.di

import androidx.lifecycle.ViewModelProvider
import by.dz.remplace3.ui.base.messenger.ActivityScreenMessenger
import by.dz.remplace3.ui.base.messenger.ScreenMessenger
import by.dz.remplace3.ui.base.navigator.ActivityNavigator
import by.dz.remplace3.ui.base.navigator.Navigator
import by.dz.remplace3.utils.ViewModelFactory
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

private const val MODULE_NAME = "Activity Module"

val activityModule = Module(MODULE_NAME, false) {
    bind<Navigator>() with singleton { ActivityNavigator(instance(), instance("navigationId")) }
    bind<ScreenMessenger>() with singleton { ActivityScreenMessenger(instance()) }
    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(instance("ActivityContext")) }
}