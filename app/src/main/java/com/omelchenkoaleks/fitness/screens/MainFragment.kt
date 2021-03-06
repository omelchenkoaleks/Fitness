package com.omelchenkoaleks.fitness.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.omelchenkoaleks.fitness.R
import com.omelchenkoaleks.fitness.databinding.FragmentMainBinding
import com.omelchenkoaleks.fitness.utils.APP_ACTIVITY

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTraining.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_dailyTrainingFragment)
        }

        binding.btnExercises.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_listExercisesFragment)
        }

        binding.btnSettings.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_settingsFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}