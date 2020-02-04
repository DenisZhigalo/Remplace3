package by.dz.remplace3.ui.screens.splash

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import by.dz.remplace3.data.repository.auth.IAuthRepository
import by.dz.remplace3.ui.base.BaseViewModel
import by.dz.remplace3.ui.base.navigator.Navigator
import by.dz.remplace3.ui.screens.main.MainNavigationActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class SplashViewModel constructor(context: Context) : BaseViewModel(), KodeinAware {

    companion object {
        const val SPLASH_DELAY = 100L
    }

    override val kodein by closestKodein(context)
    override val kodeinContext = kcontext(context)
    private val navigator: Navigator by instance()
    private val authRepository: IAuthRepository by instance()

    fun gotoMainActivity() {
        //TODO : Add authorize logic
        var activityClass: Class<out AppCompatActivity> = MainNavigationActivity::class.java
        if (authRepository.isAuthorize()) {
            activityClass = MainNavigationActivity::class.java
        }
        navigator.startActivityAndFinishByDelay(activityClass, SPLASH_DELAY)
    }

}