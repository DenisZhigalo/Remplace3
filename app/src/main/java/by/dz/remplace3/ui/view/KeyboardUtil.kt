package by.dz.remplace3.ui.view

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


class KeyboardUtil private constructor() {

    companion object {

        fun showKeyboard(view: View?) {
            view?.let {
                (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .showSoftInput(view, 0)
            }
        }

        fun hideKeyboard(view: View?) {
            view?.let {
                (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        fun hideKeyboard(activity: Activity?) {
            activity?.let { it.currentFocus.let { hideKeyboard(it) } }
        }

        fun clearFocus(view: View?) {
            hideKeyboard(view)
            clearOrSetFocusEdt(view, true)
            view?.requestFocus()
        }

        fun clearOrSetFocusEdt(v: View?, isTouch: Boolean) {
            v?.isFocusableInTouchMode = isTouch
            v?.isFocusable = isTouch
        }
    }

}