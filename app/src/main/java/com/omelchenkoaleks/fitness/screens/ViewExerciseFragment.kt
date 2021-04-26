package com.omelchenkoaleks.fitness.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omelchenkoaleks.fitness.R
import com.omelchenkoaleks.fitness.databinding.FragmentViewExerciseBinding
import com.omelchenkoaleks.fitness.utils.APP_ACTIVITY
import com.omelchenkoaleks.fitness.utils.showToast
import kotlinx.android.synthetic.main.fragment_view_exercise.*

class ViewExerciseFragment : Fragment() {

    private var _binding: FragmentViewExerciseBinding? = null
    private val binding get() = _binding!!

    private var imageId: Int? = null
    private var name: String? = null

    private var isRunning = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataFromBundle()

        binding.timer.text = ""
        imageId?.let { binding.detailImage.setImageResource(it) }
        name?.let { binding.titleExercise.text = name }

        binding.btnStart.setOnClickListener {
            if (!isRunning) {
                binding.btnStart.text = "DONE"

                object : CountDownTimer(20000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        binding.timer.text = ((millisUntilFinished + 1) / 1000).toString()
                    }

                    override fun onFinish() {
                        showToast("Finish!")
                        APP_ACTIVITY.navController.navigate(R.id.action_viewExerciseFragment_to_listExercisesFragment)
                    }
                }.start()
            } else {
                showToast("Finish!")
                APP_ACTIVITY.navController.navigate(R.id.action_viewExerciseFragment_to_listExercisesFragment)
            }

            isRunning = !isRunning
        }
    }

    private fun getDataFromBundle() {
        if (arguments?.getString("name") != "empty" && arguments?.getInt("image_id") != 0) {
            imageId = arguments?.getInt("image_id")
            name = arguments?.getString("name")
        } else {
            imageId = 0
            name = "empty"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}