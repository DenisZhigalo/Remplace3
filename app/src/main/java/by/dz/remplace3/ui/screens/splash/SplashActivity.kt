package by.dz.remplace3.ui.screens.splash

import by.dz.remplace3.R
import by.dz.remplace3.databinding.ActivitySplashBinding
import by.dz.remplace3.ui.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override fun getViewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun layoutId(): Int = R.layout.activity_splash

    override fun initViewModel(binding: ActivitySplashBinding, viewModel: SplashViewModel) {
        viewModel.gotoMainActivity()
    }
}