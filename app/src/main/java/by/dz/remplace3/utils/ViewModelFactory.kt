package by.dz.remplace3.utils

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.dz.remplace3.ui.screens.main.MainNavigationViewModel
import by.dz.remplace3.ui.screens.main.main.MainViewModel
import by.dz.remplace3.ui.screens.splash.SplashViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            // Splash
            modelClass.isAssignableFrom(SplashViewModel::class.java) ->
                SplashViewModel(context) as T

            // Main
            modelClass.isAssignableFrom(MainNavigationViewModel::class.java) ->
                MainNavigationViewModel(context) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(context) as T


            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}

inline fun <reified T : Parcelable> createParcel(crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
    object : Parcelable.Creator<T> {
        override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
        override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
    }

fun <T : Parcelable> Parcel.readParcelable(creator: Parcelable.Creator<T>): T? {
    return if (readString() != null) creator.createFromParcel(this) else null
}