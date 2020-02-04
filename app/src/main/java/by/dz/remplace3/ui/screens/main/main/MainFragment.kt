package by.dz.remplace3.ui.screens.main.main

import by.dz.remplace3.R
import by.dz.remplace3.databinding.FragmentMainBinding
import by.dz.remplace3.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override fun getViewModelClass() = MainViewModel::class.java

    override fun layoutId() = R.layout.fragment_main

    override fun fragmentInitialized() {
        binding.viewModel = viewModel
    }

}