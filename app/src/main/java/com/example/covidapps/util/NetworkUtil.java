package com.example.covidapps.util;

import android.content.Context;
import android.net.ConnectivityManager;

    public class NetworkUtil {

        public static boolean isNetworkAvailable(Context context) {
            final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
            boolean isConnected = connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
            return isConnected;
        }

        public static boolean isNetworkAvailableHome(Context context) {
            final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
            boolean isConnected = connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
            if (!isConnected) {

            }
            return isConnected;
        }
    }

