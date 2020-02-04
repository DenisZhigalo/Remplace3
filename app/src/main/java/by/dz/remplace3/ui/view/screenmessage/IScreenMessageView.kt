package by.dz.remplace3.ui.view.screenmessage

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface IScreenMessageView {

    fun prepareMessage(@StringRes textResId: Int, @DrawableRes idIcon: Int, @DrawableRes closeIcon: Int, @ColorRes bgColorRes: Int)

    fun prepareMessage(text: String, @DrawableRes idIcon: Int, @DrawableRes closeIcon: Int, @ColorRes bgColorRes: Int)

    fun showMessage(autoCloseTime: Long = 0)

    fun hideMessage(delay: Long = 0)

    fun showProgress()

    fun hideProgress()

}