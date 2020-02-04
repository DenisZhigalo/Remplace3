package by.dz.remplace3.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.dz.remplace3.R
import by.dz.remplace3.di.fragmentModule
import by.dz.remplace3.ui.view.KeyboardUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : Fragment(), KodeinAware {
    protected lateinit var binding: B
    lateinit var viewModel: VM

    override val kodeinContext: KodeinContext<*> get() = kcontext(activity)
    private val _parentKodein by closestKodein()

    override val kodein: Kodein = Kodein.lazy {
        extend(_parentKodein)
        import(fragmentModule)
    }

    private var compositeDisposable: CompositeDisposable? = null

    protected fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        bindContentView(inflater, layoutId(), container)
        fragmentInitialized()
        return binding.root
    }

    private fun bindContentView(inflater: LayoutInflater, layoutId: Int, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
    }

    abstract fun getViewModelClass(): Class<VM>

    @LayoutRes
    protected abstract fun layoutId(): Int

    open fun fragmentInitialized() {
        //For view initialize
    }

    fun setupToolbar(toolbar: Toolbar, navController: NavController) {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        toolbar.title = ""
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setNavigationOnClickListener {
            if (!navController.popBackStack()) {
                activity?.finish()
            }
        }

        navController.addOnDestinationChangedListener { _, _, _ ->
            KeyboardUtil.hideKeyboard(activity)
        }
    }

    override fun onPause() {
        compositeDisposable?.let {
            it.dispose()
            it.clear()
            compositeDisposable = null
        }
        super.onPause()
    }

}