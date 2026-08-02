package com.example.trackify

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.Locale

class SummaryFragment : Fragment(R.layout.fragment_summary) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("TrackifyPrefs", Context.MODE_PRIVATE)

        // Get Goals (Targets)
        val goalStudy = sharedPref.getFloat("goal_study", 0f)
        val goalSteps = sharedPref.getInt("goal_steps", 0)
        val goalWater = sharedPref.getInt("goal_water", 0)
        val goalSleep = sharedPref.getFloat("goal_sleep", 0f)

        // Get Today's Performance
        val todayStudy = sharedPref.getFloat("today_study", 0f)
        val todaySteps = sharedPref.getInt("today_steps", 0)
        val todayWater = sharedPref.getInt("today_water", 0)
        val todaySleep = sharedPref.getFloat("today_sleep", 0f)
        val todayLearnt = sharedPref.getString("today_learnt", "Nothing recorded yet.")

        // Calculate Efficiency
        // Formula: average of (actual/goal) capped at 100% for each metric
        val studyEff = if (goalStudy > 0) (todayStudy / goalStudy).coerceAtMost(1f) else 0f
        val stepsEff = if (goalSteps > 0) (todaySteps.toFloat() / goalSteps).coerceAtMost(1f) else 0f
        val waterEff = if (goalWater > 0) (todayWater.toFloat() / goalWater).coerceAtMost(1f) else 0f
        val sleepEff = if (goalSleep > 0) (todaySleep / goalSleep).coerceAtMost(1f) else 0f

        val totalEfficiency = ((studyEff + stepsEff + waterEff + sleepEff) / 4f) * 100

        // Update UI
        val tvName = view.findViewById<TextView>(R.id.tv_summary_name)
        val tvDate = view.findViewById<TextView>(R.id.tv_summary_date)
        val userName = sharedPref.getString("user_name", "User")
        val todayDate = sharedPref.getString("today_date", "--")
        
        tvName.text = "Hello, $userName!"
        tvDate.text = "Date: $todayDate"

        view.findViewById<TextView>(R.id.tv_summary_efficiency).text = 
            String.format(Locale.getDefault(), "Today's Efficiency: %.0f%%", totalEfficiency)

        view.findViewById<TextView>(R.id.tv_sum_study).text = "$todayStudy / $goalStudy hrs"
        view.findViewById<TextView>(R.id.tv_sum_steps).text = "$todaySteps / $goalSteps"
        view.findViewById<TextView>(R.id.tv_sum_water).text = "$todayWater / $goalWater glasses"
        view.findViewById<TextView>(R.id.tv_sum_sleep).text = "$todaySleep / $goalSleep hrs"
        
        // Show only the user input for "What I Learnt"
        view.findViewById<TextView>(R.id.tv_sum_learnt).text = todayLearnt
    }
}