package com.application.architecture.views.utils

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import androidx.fragment.app.FragmentActivity
import com.application.architecture.R
//import coil.api.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.text.DecimalFormat


fun Context.T(msg: String) {
    GlobalScope.launch {
        withContext(Dispatchers.Main) {
            Toast.makeText(this@T, msg, Toast.LENGTH_LONG).show()
        }
    }

}


fun Context.loadJSONFromAssets(fileName: String): String {
    return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}

fun Context.checkPermission(permission: String): Boolean {

    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED


}


fun Context.isLocationEnabled(): Boolean {
    val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return LocationManagerCompat.isLocationEnabled(locationManager)
}


fun FragmentActivity.closeKeypad() {
    val view = this.currentFocus
    view?.let { v ->
        val imm =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }
}

fun Context.showKeyboard() {

    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (getKeyboardHeight(imm) == 0) {
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

}

fun getKeyboardHeight(imm: InputMethodManager): Int =
    InputMethodManager::class.java.getMethod("getInputMethodWindowVisibleHeight").invoke(imm) as Int


fun Context.loadImage(url: String?, imageView: ImageView) {
    url?.also {
        Glide.with(imageView.context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
//                    .error(R.drawable.secure_logo)
            )
            .into(imageView)

    }


}

fun Context.getTwoDecimalPlaces(value: Double): String = DecimalFormat("0.00").format(value)

fun Context.getMetaDataValue(key: String): String? {
    packageManager.getApplicationInfo(
        packageName,
        PackageManager.GET_META_DATA
    ).apply {

        return try {
            metaData.getString(key)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }

}

fun Context.isGpsEnabled() =
    (getSystemService(Context.LOCATION_SERVICE) as LocationManager).isProviderEnabled(
        LocationManager.GPS_PROVIDER
    )