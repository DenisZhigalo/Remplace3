package by.dz.remplace3.ui.base.navigator

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

abstract class Navigator {
    abstract fun getActivity(): AppCompatActivity

    @IdRes
    abstract fun getNavigationViewId(): Int

    fun startActivity(activityClass: Class<out AppCompatActivity>, args: Bundle = Bundle()) {
        val activity = getActivity()
        val intent = Intent(activity, activityClass)
        intent.putExtras(args)
        activity.startActivity(intent)
    }

    fun startActivityAndFinish(activityClass: Class<out AppCompatActivity>) {
        val activity = getActivity()
        val intent = Intent(activity, activityClass)
        activity.startActivity(intent)
        activity.finish()
    }

    fun startActivityAndFinishByDelay(
        activityClass: Class<out AppCompatActivity>,
        delayInMillis: Long
    ) {
        Handler().postDelayed({
            startActivity(activityClass)
            getActivity().overridePendingTransition(0, 0)
            getActivity().finish()
        }, delayInMillis)
    }

    fun startActivityWithAnimation(
        activityClass: Class<out AppCompatActivity>,
        inAnimation: Int, outAnimation: Int
    ) {
        val activity = getActivity()
        val intent = Intent(activity, activityClass)
        activity.startActivity(intent)
        getActivity().overridePendingTransition(inAnimation, outAnimation)
    }

    fun startActivityWithAnimation(
        activityClass: Class<out AppCompatActivity>,
        inAnimation: Int, outAnimation: Int, args: Bundle
    ) {
        val activity = getActivity()
        val intent = Intent(activity, activityClass)
        intent.putExtras(args)
        activity.startActivity(intent)
        getActivity().overridePendingTransition(inAnimation, outAnimation)
    }

    fun startActivityForResult(
        activityClass: Class<out AppCompatActivity>,
        args: Bundle? = null,
        requestCode: Int,
        animation: Boolean = true
    ) {
        val activity = getActivity()
        val intent = Intent(activity, activityClass)
        args?.let {
            intent.putExtras(it)
        }
        if (!animation) {
            getActivity().overridePendingTransition(0, 0)
        }
        getActivity().startActivityForResult(intent, requestCode)
        if (!animation) {
            getActivity().overridePendingTransition(0, 0)
        }
    }

    fun startActivityWithAnimationForResult(
        activityClass: Class<out AppCompatActivity>,
        inAnimation: Int, outAnimation: Int, args: Bundle, requestCode: Int
    ) {
        val activity = getActivity()
        val intent = Intent(activity, activityClass)
        intent.putExtras(args)
        getActivity().startActivityForResult(intent, requestCode)
        getActivity().overridePendingTransition(inAnimation, outAnimation)
    }

    fun finishActivityWithAnimation(inAnimation: Int, outAnimation: Int) {
        getActivity().finish()
        getActivity().overridePendingTransition(inAnimation, outAnimation)
    }

    fun finishActivity() {
        getActivity().finish()
    }

    fun setResult(resultCode: Int, bundle: Bundle? = null) {
        val returnIntent = Intent()
        bundle?.let {
            returnIntent.putExtras(it)
        }
        getActivity().setResult(resultCode, returnIntent)
    }

    fun finishActivityWithResult(resultCode: Int, bundle: Bundle? = null) {
        val returnIntent = Intent()
        bundle?.let {
            returnIntent.putExtras(it)
        }
        getActivity().setResult(resultCode, returnIntent)
        getActivity().finish()
    }

    fun finishActivityWithAnimationForResult(inAnimation: Int, outAnimation: Int, resultCode: Int) {
        val returnIntent = Intent()
        getActivity().setResult(resultCode, returnIntent)
        getActivity().finish()
        getActivity().overridePendingTransition(inAnimation, outAnimation)
    }

    fun getNavController() = getActivity().findNavController(getNavigationViewId())

    fun popBackStack() {
        getNavController().popBackStack()
    }

    fun navigate(@IdRes transaction: Int, bundle: Bundle = Bundle()) {
        getNavController().navigate(transaction, bundle)
    }

    fun navigate(navDirections: NavDirections) {
        getNavController().navigate(navDirections)
    }

}