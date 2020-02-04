package by.dz.remplace3.ui.base.navigator

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

class ActivityNavigator constructor(
    private val activity: AppCompatActivity, @IdRes private val navigationViewId: Int
) : Navigator() {

    override fun getActivity(): AppCompatActivity = activity

    override fun getNavigationViewId() = navigationViewId
}