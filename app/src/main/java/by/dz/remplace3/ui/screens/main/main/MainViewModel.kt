package by.dz.remplace3.ui.screens.main.main

import android.content.Context
import by.dz.remplace3.ui.base.BaseViewModel
import by.dz.remplace3.ui.base.navigator.Navigator
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class MainViewModel constructor(context: Context) : BaseViewModel(), KodeinAware {
    override val kodein by closestKodein(context)
    override val kodeinContext = kcontext(context)

    val navigator: Navigator by instance()

}