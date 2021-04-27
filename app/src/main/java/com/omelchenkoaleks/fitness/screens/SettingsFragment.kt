package com.omelchenkoaleks.fitness.screens

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.omelchenkoaleks.fitness.R
import com.omelchenkoaleks.fitness.databinding.FragmentSettingsBinding
import com.omelchenkoaleks.fitness.notifications.AlarmNotificationReceiver
import com.omelchenkoaleks.fitness.utils.APP_ACTIVITY
import com.omelchenkoaleks.fitness.utils.AppPreference
import com.omelchenkoaleks.fitness.utils.showToast
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mode = AppPreference.getMode()
        setRadioButton(mode)

        binding.btnSaveSettings.setOnClickListener {
            saveWorkoutMode()
            saveAlarm(binding.switchAlarm.isChecked)
            APP_ACTIVITY.navController.navigate(R.id.action_settingsFragment_to_mainFragment)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun saveAlarm(checked: Boolean) {

        val intent: Intent
        val pendingIntent: PendingIntent

        if (checked) {
            val manager: AlarmManager =
                APP_ACTIVITY.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            intent = Intent(activity, AlarmNotificationReceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0)

            // set time to alarm
            val calendar = Calendar.getInstance()
            val toDay: Date = Calendar.getInstance().time
            calendar.set(toDay.year, toDay.month, toDay.day, time_picker.hour, time_picker.minute)

            manager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )

            showToast("alarm will wake at: ${time_picker.hour}:${time_picker.minute}")

        } else {
            // cancel alarm
            intent = Intent(activity, AlarmNotificationReceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0)
            val manager: AlarmManager =
                APP_ACTIVITY.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            manager.cancel(pendingIntent)
        }

    }

    private fun saveWorkoutMode() {
        when (binding.radioGroup.checkedRadioButtonId) {
            binding.radioButtonEasy.id -> {
                AppPreference.setMode(0)
            }
            binding.radioButtonMedium.id -> {
                AppPreference.setMode(1)
            }
            binding.radioButtonHard.id -> {
                AppPreference.setMode(2)
            }
        }
    }

    private fun setRadioButton(mode: Int) {
        when (mode) {
            0 -> {
                binding.radioGroup.check(R.id.radio_button_easy)
            }
            1 -> {
                binding.radioGroup.check(R.id.radio_button_medium)
            }
            2 -> {
                binding.radioGroup.check(R.id.radio_button_hard)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}