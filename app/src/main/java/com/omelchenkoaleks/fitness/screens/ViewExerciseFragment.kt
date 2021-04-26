package com.omelchenkoaleks.fitness.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omelchenkoaleks.fitness.R
import com.omelchenkoaleks.fitness.databinding.FragmentViewExerciseBinding
import com.omelchenkoaleks.fitness.utils.showToast

class ViewExerciseFragment : Fragment() {

    private var _binding: FragmentViewExerciseBinding? = null
    private val binding get() = _binding!!

    private var imageId: Int? = null
    private var name: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataFromBundle()
        showToast("$imageId and $name")
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