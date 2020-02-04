package by.dz.remplace3.utils

import android.content.Context
import android.net.ConnectivityManager

class NetManager(private val applicationContext: Context) : INetManager {
    override fun isConnectedToInternet(): Boolean {
        try {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        } catch (e: Exception) {
            //DO NOTHING
        }
        return false
    }

    override fun isDisconnectedToInternet() = !isConnectedToInternet()
}