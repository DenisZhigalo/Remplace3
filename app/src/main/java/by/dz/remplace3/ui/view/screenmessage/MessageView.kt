package by.dz.remplace3.ui.view.screenmessage

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import by.dz.remplace3.R
import by.dz.remplace3.ui.view.impl.AnimationListenerImpl
import kotlinx.android.synthetic.main.view_message.view.*

class MessageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0, defStyleRes: Int = 0
) : RelativeLayout(context, attrs, defStyle, defStyleRes) {

    interface OnButtonClickListener {
        fun onButtonCloseClick()
    }

    var onButtonClickListener: OnButtonClickListener? = null
    private var showAnimation: Animation
    private var hideAnimation: Animation

    private var autoClose = false

    init {
        LayoutInflater.from(context).inflate(R.layout.view_message, this, true)

        iconCloseImageView.setOnClickListener {
            if (visibility != View.VISIBLE) {
                return@setOnClickListener
            }
            if (onButtonClickListener != null) {
                onButtonClickListener?.onButtonCloseClick()
            }
            hideMessage()
        }

        showAnimation = AnimationUtils.loadAnimation(context, R.anim.message_show)
        showAnimation.setAnimationListener(object : AnimationListenerImpl() {
            override fun onAnimationStart(animation: Animation?) {
                visibility = View.VISIBLE
            }
        })

        hideAnimation = AnimationUtils.loadAnimation(context, R.anim.message_hide)
        hideAnimation.setAnimationListener(object : AnimationListenerImpl() {
            override fun onAnimationEnd(animation: Animation?) {
                visibility = View.GONE
            }
        })
    }

    fun prepareMessage(@StringRes textResId: Int, @DrawableRes idIcon: Int, @DrawableRes closeIcon: Int, @ColorRes bgColorRes: Int) {
        messageTextView.setText(textResId)
        prepareMessage(idIcon, closeIcon, bgColorRes)
    }

    fun prepareMessage(text: String, @DrawableRes idIcon: Int, @DrawableRes closeIcon: Int, @ColorRes bgColorRes: Int) {
        messageTextView.text = text
        prepareMessage(idIcon, closeIcon, bgColorRes)
    }

    private fun prepareMessage(@DrawableRes idIcon: Int, @DrawableRes closeIcon: Int, @ColorRes bgColorRes: Int) {
        if (visibility == View.VISIBLE) {
            visibility = View.INVISIBLE
        }
        iconImageView.setImageResource(idIcon)
        iconCloseImageView.setImageResource(closeIcon)
        bgContainer.setBackgroundResource(bgColorRes)
    }

    fun showMessage(autoCloseTime: Long = 0) {
        if (visibility == View.VISIBLE) {
            visibility = View.INVISIBLE
        }
        autoClose = autoCloseTime > 0
        startAnimation(showAnimation)
        if (!autoClose) {
            return
        }
        postDelayed({
            if (autoClose) {
                hideMessage()
            }
        }, autoCloseTime)
    }

    fun hideMessage(delay: Long = 0) {
        if (delay > 0) {
            postDelayed({
                hideMessage()
            }, delay)
            return
        }
        autoClose = false
        if (visibility == View.VISIBLE) {
            startAnimation(hideAnimation)
        }
    }

}