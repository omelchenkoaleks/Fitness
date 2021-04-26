package com.omelchenkoaleks.fitness.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omelchenkoaleks.fitness.R
import com.omelchenkoaleks.fitness.adapters.RecyclerViewAdapter
import com.omelchenkoaleks.fitness.databinding.FragmentListExercisesBinding
import com.omelchenkoaleks.fitness.models.Exercise
import com.omelchenkoaleks.fitness.utils.APP_ACTIVITY

class ListExercisesFragment : Fragment() {

    private var _binding: FragmentListExercisesBinding? = null
    private val binding get() = _binding!!

    private var exercisesList: MutableList<Exercise> = mutableListOf()
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        recyclerViewAdapter = RecyclerViewAdapter(exercisesList)
        layoutManager = LinearLayoutManager(APP_ACTIVITY)
        binding.listExercises.layoutManager = layoutManager
        binding.listExercises.adapter = recyclerViewAdapter
    }

    private fun initData() {
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
        exercisesList.add(Exercise(R.drawable.ic_play_90, "Easy Pose"))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}