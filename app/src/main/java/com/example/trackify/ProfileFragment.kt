package com.example.trackify

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("TrackifyPrefs", Context.MODE_PRIVATE)

        view.findViewById<TextView>(R.id.tv_prof_name).text = sharedPref.getString("user_name", "N/A")
        view.findViewById<TextView>(R.id.tv_prof_age).text = sharedPref.getString("user_age", "N/A")
        view.findViewById<TextView>(R.id.tv_prof_goal_study).text = "${sharedPref.getFloat("goal_study", 0f)} hrs"
        view.findViewById<TextView>(R.id.tv_prof_goal_steps).text = "${sharedPref.getInt("goal_steps", 0)} steps"
        view.findViewById<TextView>(R.id.tv_prof_goal_water).text = "${sharedPref.getInt("goal_water", 0)} glasses"
        view.findViewById<TextView>(R.id.tv_prof_goal_sleep).text = "${sharedPref.getFloat("goal_sleep", 0f)} hrs"
    }
}