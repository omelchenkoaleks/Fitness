package com.omelchenkoaleks.fitness.screens

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omelchenkoaleks.fitness.R
import com.omelchenkoaleks.fitness.databinding.FragmentDailyTrainingBinding
import com.omelchenkoaleks.fitness.models.Exercise
import com.omelchenkoaleks.fitness.utils.*
import java.util.*

class DailyTrainingFragment : Fragment() {

    private var _binding: FragmentDailyTrainingBinding? = null
    private val binding get() = _binding!!

    private var exerciseId = 0
    private val list = mutableListOf<Exercise>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        // set data
        binding.progressBar.max = list.size

        setExerciseInformation(exerciseId)

        binding.btnStart.setOnClickListener {
            when {
                binding.btnStart.text.toString().toLowerCase(Locale.ROOT) == "start" -> {
                    showGetReady()
                    binding.btnStart.text = "Done"
                }
                binding.btnStart.text.toString().toLowerCase(Locale.ROOT) == "done" -> {

                    when (AppPreference.getMode()) {
                        0 -> {
                            exercisesEasyModeCountDownTimer.cancel()
                        }
                        1 -> {
                            exercisesMediumModeCountDownTimer.cancel()
                        }
                        2 -> {
                            exercisesHardModeCountDownTimer.cancel()
                        }
                    }

                    restTimeCountDownTimer.cancel()

                    if (exerciseId < list.size) {
                        showRestTime()
                        exerciseId++
                        binding.progressBar.progress = exerciseId
                        binding.timer.text = ""
                    } else {
                        showFinished()
                    }
                }
                else -> {
                    when (AppPreference.getMode()) {
                        0 -> {
                            exercisesEasyModeCountDownTimer.cancel()
                        }
                        1 -> {
                            exercisesMediumModeCountDownTimer.cancel()
                        }
                        2 -> {
                            exercisesHardModeCountDownTimer.cancel()
                        }
                    }

                    restTimeCountDownTimer.cancel()

                    if (exerciseId < list.size) {
                        setExerciseInformation(exerciseId)
                    } else {
                        showFinished()
                    }
                }
            }
        }
    }

    private fun showRestTime() {
        binding.detailImage.visibility = View.INVISIBLE
        binding.btnStart.text = "Skip"
        binding.btnStart.visibility = View.VISIBLE
        binding.timer.visibility = View.INVISIBLE

        binding.countdown.visibility = View.VISIBLE
        binding.getReady.visibility = View.VISIBLE

        restTimeCountDownTimer.start()

        binding.getReady.text = "REST TIME"
    }

    private fun showGetReady() {
        binding.detailImage.visibility = View.INVISIBLE
        binding.btnStart.visibility = View.INVISIBLE
        binding.timer.visibility = View.VISIBLE

        binding.countdown.visibility = View.VISIBLE
        binding.getReady.visibility = View.VISIBLE

        binding.getReady.text = "GET READY"

        object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.countdown.text = ((millisUntilFinished + (1 - 1000)) / 1000).toString()
            }

            override fun onFinish() {
                showExercises()
            }
        }.start()

    }

    private val exercisesEasyModeCountDownTimer: CountDownTimer = object : CountDownTimer(
        TIME_LIMIT_EASY, 1000
    ) {
        override fun onTick(millisUntilFinished: Long) {
            binding.timer.text = ((millisUntilFinished + 1) / 1000).toString()
        }

        override fun onFinish() {
            if (exerciseId < list.size - 1) {
                exerciseId++
                binding.progressBar.progress = exerciseId
                binding.timer.text = ""

                setExerciseInformation(exerciseId)
                binding.btnStart.text = "Start"
            } else {
                showFinished()
            }
        }
    }

    private val exercisesMediumModeCountDownTimer: CountDownTimer = object : CountDownTimer(
        TIME_LIMIT_MEDIUM, 1000
    ) {
        override fun onTick(millisUntilFinished: Long) {
            binding.timer.text = ((millisUntilFinished + 1) / 1000).toString()
        }

        override fun onFinish() {
            if (exerciseId < list.size - 1) {
                exerciseId++
                binding.progressBar.progress = exerciseId
                binding.timer.text = ""

                setExerciseInformation(exerciseId)
                binding.btnStart.text = "Start"
            } else {
                showFinished()
            }
        }
    }

    private val exercisesHardModeCountDownTimer: CountDownTimer = object : CountDownTimer(
        TIME_LIMIT_HARD, 1000
    ) {
        override fun onTick(millisUntilFinished: Long) {
            binding.timer.text = ((millisUntilFinished + 1) / 1000).toString()
        }

        override fun onFinish() {
            if (exerciseId < list.size - 1) {
                exerciseId++
                binding.progressBar.progress = exerciseId
                binding.timer.text = ""

                setExerciseInformation(exerciseId)
                binding.btnStart.text = "Start"
            } else {
                showFinished()
            }
        }
    }

    private val restTimeCountDownTimer: CountDownTimer = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            binding.countdown.text = ((millisUntilFinished + 1) / 1000).toString()
        }

        override fun onFinish() {
            setExerciseInformation(exerciseId)
            showExercises()
        }
    }

    private fun showExercises() {
        if (exerciseId < list.size) {
            binding.detailImage.visibility = View.VISIBLE
            binding.btnStart.visibility = View.VISIBLE
            binding.countdown.visibility = View.INVISIBLE
            binding.getReady.visibility = View.INVISIBLE

            when (AppPreference.getMode()) {
                0 -> {
                    exercisesEasyModeCountDownTimer.start()
                }
                1 -> {
                    exercisesMediumModeCountDownTimer.start()
                }
                2 -> {
                    exercisesHardModeCountDownTimer.start()
                }
            }

            // set data
            binding.detailImage.setImageResource(list[exerciseId].image_id)
            binding.titleExercise.text = list[exerciseId].name
        } else {
            showFinished()
        }
    }

    private fun showFinished() {
        binding.detailImage.visibility = View.INVISIBLE
        binding.btnStart.visibility = View.INVISIBLE
        binding.timer.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.INVISIBLE
        binding.countdown.visibility = View.VISIBLE
        binding.getReady.visibility = View.VISIBLE

        binding.getReady.text = "FINISHED!!!"
        binding.countdown.text = "Congratulation! \nYou're done with today exercises"
        binding.countdown.textSize = 20F

        AppPreference.saveDay("${Calendar.getInstance().timeInMillis}")
    }

    private fun setExerciseInformation(exerciseId: Int) {
        binding.detailImage.setImageResource(list[exerciseId].image_id)
        binding.titleExercise.text = list[exerciseId].name
        binding.btnStart.text = "Start"

        binding.detailImage.visibility = View.VISIBLE
        binding.btnStart.visibility = View.VISIBLE
        binding.timer.visibility = View.VISIBLE

        binding.countdown.visibility = View.INVISIBLE
        binding.getReady.visibility = View.INVISIBLE
    }

    private fun initData() {
        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
//        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
//        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
//        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
//        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
//        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
//        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
//        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
//        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
//        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
//        list.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}