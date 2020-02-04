package by.dz.remplace3.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import by.dz.remplace3.di.activityModule
import by.dz.remplace3.ui.view.screenmessage.IScreenMessageView
import org.kodein.di.Copy
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : AppCompatActivity(),
    KodeinAware, IScreenMessageView {
    protected lateinit var binding: B
    lateinit var viewModel: VM
    private val _parentKodein by closestKodein()

    override val kodein: Kodein by retainedKodein {
        extend(_parentKodein, copy = Copy.All)
        bind<AppCompatActivity>() with singleton { this@BaseActivity }
        bind<Int>("navigationId") with singleton { navigationId() }
        bind<Context>("ActivityContext") with singleton { this@BaseActivity }
        bind<IScreenMessageView>() with singleton { this@BaseActivity }
        import(activityModule)
    }

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindContentView(layoutId())
    }

    private fun bindContentView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
        initViewModel(binding, viewModel)
        binding.executePendingBindings()
    }

    open fun initViewModel(binding: B, viewModel: VM) {
        //To initialize local binding fields (e.g. binding.viewModel = viewModel)
    }

    abstract fun getViewModelClass(): Class<VM>

    @LayoutRes
    protected abstract fun layoutId(): Int

    @IdRes
    open fun navigationId() = -1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        supportFragmentManager.fragments.forEach { fragment ->
            fragment.onActivityResult(requestCode, resultCode, data)
            fragment.childFragmentManager.fragments.forEach { childFragment ->
                childFragment.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    override fun prepareMessage(textResId: Int, idIcon: Int, closeIcon: Int, bgColorRes: Int) {
        //FOR IMPL IF NEED
    }

    override fun prepareMessage(text: String, idIcon: Int, closeIcon: Int, bgColorRes: Int) {
        //FOR IMPL IF NEED
    }

    override fun showMessage(autoCloseTime: Long) {
        //FOR IMPL IF NEED
    }

    override fun hideMessage(delay: Long) {
        //FOR IMPL IF NEED
    }

    override fun showProgress() {
        //FOR IMPL IF NEED
    }

    override fun hideProgress() {
        //FOR IMPL IF NEED
    }
}