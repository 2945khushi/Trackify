package com.example.trackify

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class DetailsFragment : Fragment(R.layout.fragment_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etName = view.findViewById<EditText>(R.id.et_name)
        val etAge = view.findViewById<EditText>(R.id.et_age)
        val etGoalStudy = view.findViewById<EditText>(R.id.et_goal_study)
        val etGoalSteps = view.findViewById<EditText>(R.id.et_goal_steps)
        val etGoalWater = view.findViewById<EditText>(R.id.et_goal_water)
        val etGoalSleep = view.findViewById<EditText>(R.id.et_goal_sleep)
        val btnSave = view.findViewById<Button>(R.id.btn_save_details)

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString()
            val goalStudy = etGoalStudy.text.toString().toFloatOrNull() ?: 0f
            val goalSteps = etGoalSteps.text.toString().toIntOrNull() ?: 0
            val goalWater = etGoalWater.text.toString().toIntOrNull() ?: 0
            val goalSleep = etGoalSleep.text.toString().toFloatOrNull() ?: 0f

            if (name.isEmpty() || age.isEmpty() || goalStudy == 0f || goalSteps == 0 || goalWater == 0 || goalSleep == 0f) {
                Toast.makeText(context, "Please fill all fields and targets!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = requireActivity().getSharedPreferences("TrackifyPrefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("user_name", name)
                putString("user_age", age)
                putFloat("goal_study", goalStudy)
                putInt("goal_steps", goalSteps)
                putInt("goal_water", goalWater)
                putFloat("goal_sleep", goalSleep)
                apply()
            }

            findNavController().navigate(R.id.action_detailsFragment_to_inputFragment)
        }
    }
}