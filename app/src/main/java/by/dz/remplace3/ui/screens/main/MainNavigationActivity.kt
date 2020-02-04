package by.dz.remplace3.ui.screens.main

import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import by.dz.remplace3.R
import by.dz.remplace3.databinding.ActivityMainBinding
import by.dz.remplace3.ui.base.BaseActivity
import by.dz.remplace3.ui.view.KeyboardUtil

class MainNavigationActivity : BaseActivity<ActivityMainBinding, MainNavigationViewModel>() {

    override fun getViewModelClass(): Class<MainNavigationViewModel> =
        MainNavigationViewModel::class.java

    override fun layoutId(): Int = R.layout.activity_main

    override fun navigationId() = R.id.main_nav_host_fragment

    override fun initViewModel(binding: ActivityMainBinding, viewModel: MainNavigationViewModel) {
        binding.viewModel = viewModel
        val navController = findNavController(navigationId())
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        navController.addOnDestinationChangedListener { nController, destination, _ ->
            binding.loginMessageView.hideMessage()
            KeyboardUtil.hideKeyboard(this)
        }
    }

    override fun prepareMessage(textResId: Int, idIcon: Int, closeIcon: Int, bgColorRes: Int) {
        binding.loginMessageView.prepareMessage(textResId, idIcon, closeIcon, bgColorRes)
    }

    override fun prepareMessage(text: String, idIcon: Int, closeIcon: Int, bgColorRes: Int) {
        binding.loginMessageView.prepareMessage(text, idIcon, closeIcon, bgColorRes)
    }

    override fun showMessage(autoCloseTime: Long) {
        binding.loginMessageView.showMessage(autoCloseTime)
    }

    override fun hideMessage(delay: Long) {
        binding.loginMessageView.hideMessage()
    }

    override fun showProgress() {
        binding.loaderContainer.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.loaderContainer.visibility = View.GONE
    }

    override fun onBackPressed() {
        if (binding.loaderContainer.visibility == View.VISIBLE) {
            return
        }
        super.onBackPressed()
    }
}