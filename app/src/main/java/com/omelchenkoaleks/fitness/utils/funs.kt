package com.omelchenkoaleks.fitness.utils

import android.widget.Toast

fun showToast(massage: String) {
    Toast.makeText(APP_ACTIVITY, massage, Toast.LENGTH_LONG).show()
}