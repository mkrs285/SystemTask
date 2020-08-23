package com.example.systemtask.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectionManager private constructor() {
    /*
     * returns true if connected to Internet
     */
    fun isConnectingToInternet(context: Context): Boolean {
        var isConnected = false
        val connectivity = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null) for (i in info.indices) if (info[i].state == NetworkInfo.State.CONNECTED) {
                isConnected = true
            }
        }
        /* if (!isConnected) {
            Utils.displayToast(context, "Please check internet connection.");
        }*/return isConnected
    }

    companion object {
        // providing global point to access
        var singletonInstance: ConnectionManager? = null
            get() {
                if (null == field) {
                    field = ConnectionManager()
                }
                return field
            }
            private set
    }
}