package by.dz.remplace3.ui.base.messenger

import by.dz.remplace3.R
import by.dz.remplace3.ui.view.screenmessage.IScreenMessageView
import by.dz.remplace3.ui.view.screenmessage.IScreenMessenger

abstract class ScreenMessenger : IScreenMessenger {

    abstract fun getMessageView(): IScreenMessageView

    override fun showErrorMessage(textResId: Int, autoCloseTime: Long) {
        showMessage(
            textResId, R.drawable.ic_error, R.drawable.ic_close,
            R.color.colorMessageError, autoCloseTime
        )
    }

    override fun showErrorMessage(text: String, autoCloseTime: Long) {
        showMessage(
            text, R.drawable.ic_error, R.drawable.ic_close,
            R.color.colorMessageError, autoCloseTime
        )
    }

    override fun showErrorMessage(textResId: Int, idIcon: Int, autoCloseTime: Long) {
        showMessage(
            textResId, idIcon, R.drawable.ic_close,
            R.color.colorMessageError, autoCloseTime
        )
    }

    override fun showErrorMessage(text: String, idIcon: Int, autoCloseTime: Long) {
        showMessage(text, idIcon, R.drawable.ic_close, R.color.colorMessageError, autoCloseTime)
    }

    override fun showSuccessMessage(textResId: Int, autoCloseTime: Long) {
        showMessage(
            textResId, R.drawable.ic_ok, R.drawable.ic_close,
            R.color.colorMessageSuccess, autoCloseTime
        )
    }

    override fun showSuccessMessage(text: String, autoCloseTime: Long) {
        showMessage(
            text, R.drawable.ic_ok, R.drawable.ic_close,
            R.color.colorMessageSuccess, autoCloseTime
        )
    }

    override fun showSuccessMessage(textResId: Int, idIcon: Int, autoCloseTime: Long) {
        showMessage(
            textResId, idIcon, R.drawable.ic_close,
            R.color.colorMessageSuccess, autoCloseTime
        )
    }

    override fun showSuccessMessage(text: String, idIcon: Int, autoCloseTime: Long) {
        showMessage(text, idIcon, R.drawable.ic_close, R.color.colorMessageSuccess, autoCloseTime)
    }

    override fun showMessage(textResId: Int, idIcon: Int, bgColorRes: Int, autoCloseTime: Long) {
        showMessage(textResId, idIcon, R.drawable.ic_close, bgColorRes, autoCloseTime)
    }

    override fun showMessage(text: String, idIcon: Int, bgColorRes: Int, autoCloseTime: Long) {
        showMessage(text, idIcon, R.drawable.ic_close, bgColorRes, autoCloseTime)
    }

    override fun showMessage(
        textResId: Int, idIcon: Int, closeIcon: Int,
        bgColorRes: Int, autoCloseTime: Long
    ) {
        getMessageView().prepareMessage(textResId, idIcon, closeIcon, bgColorRes)
        getMessageView().showMessage(autoCloseTime)
    }

    override fun showMessage(
        text: String, idIcon: Int, closeIcon: Int,
        bgColorRes: Int, autoCloseTime: Long
    ) {
        getMessageView().prepareMessage(text, idIcon, closeIcon, bgColorRes)
        getMessageView().showMessage(autoCloseTime)
    }

    fun showDisconnectError() {
        showErrorMessage(R.string.error_disconnect)
    }

    override fun hideMessage(delay: Long) {
        getMessageView().hideMessage(delay)
    }

    override fun showProgress() {
        getMessageView().showProgress()
    }

    override fun hideProgress() {
        getMessageView().hideProgress()
    }
}