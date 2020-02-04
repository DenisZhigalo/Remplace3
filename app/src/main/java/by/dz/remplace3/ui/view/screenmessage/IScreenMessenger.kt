package by.dz.remplace3.ui.view.screenmessage

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface IScreenMessenger {

    fun showErrorMessage(@StringRes textResId: Int, autoCloseTime: Long = 0)

    fun showErrorMessage(text: String, autoCloseTime: Long = 0)

    fun showErrorMessage(@StringRes textResId: Int, @DrawableRes idIcon: Int, autoCloseTime: Long = 0)

    fun showErrorMessage(text: String, @DrawableRes idIcon: Int, autoCloseTime: Long = 0)


    fun showSuccessMessage(@StringRes textResId: Int, autoCloseTime: Long = 0)

    fun showSuccessMessage(text: String, autoCloseTime: Long = 0)

    fun showSuccessMessage(@StringRes textResId: Int, @DrawableRes idIcon: Int, autoCloseTime: Long = 0)

    fun showSuccessMessage(text: String, @DrawableRes idIcon: Int, autoCloseTime: Long = 0)


    fun showMessage(
        @StringRes textResId: Int, @DrawableRes idIcon: Int, @ColorRes bgColorRes: Int,
        autoCloseTime: Long = 0
    )

    fun showMessage(
        text: String, @DrawableRes idIcon: Int, @ColorRes bgColorRes: Int,
        autoCloseTime: Long = 0
    )

    fun showMessage(
        @StringRes textResId: Int, @DrawableRes idIcon: Int, @DrawableRes closeIcon: Int, @ColorRes bgColorRes: Int,
        autoCloseTime: Long = 0
    )

    fun showMessage(
        text: String, @DrawableRes idIcon: Int, @DrawableRes closeIcon: Int, @ColorRes bgColorRes: Int,
        autoCloseTime: Long = 0
    )

    fun hideMessage(delay: Long = 0)

    fun showProgress()

    fun hideProgress()

}