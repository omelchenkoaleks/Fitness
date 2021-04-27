package com.omelchenkoaleks.fitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.omelchenkoaleks.fitness.notifications.NotificationUtils
import com.omelchenkoaleks.fitness.utils.APP_ACTIVITY
import com.omelchenkoaleks.fitness.utils.AppPreference

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        APP_ACTIVITY = this

        navController = Navigation.findNavController(this, R.id.nav_host_fragments)
        AppPreference.getPreference(this)
        NotificationUtils(this)
    }
}