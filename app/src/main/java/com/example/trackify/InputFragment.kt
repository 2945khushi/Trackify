package com.example.trackify

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InputFragment : Fragment(R.layout.fragment_input) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvDate = view.findViewById<TextView>(R.id.tv_input_date)
        val currentDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())
        tvDate.text = "Date: $currentDate"

        val etStudy = view.findViewById<EditText>(R.id.et_today_study)
        val etWater = view.findViewById<EditText>(R.id.et_today_water)
        val etSleep = view.findViewById<EditText>(R.id.et_today_sleep)
        val etSteps = view.findViewById<EditText>(R.id.et_today_steps)
        val etLearnt = view.findViewById<EditText>(R.id.et_today_learnt)
        val rbRating = view.findViewById<RatingBar>(R.id.rb_day_rating)
        val btnSubmit = view.findViewById<Button>(R.id.btn_submit_progress)

        btnSubmit.setOnClickListener {
            val study = etStudy.text.toString().toFloatOrNull() ?: 0f
            val water = etWater.text.toString().toIntOrNull() ?: 0
            val sleep = etSleep.text.toString().toFloatOrNull() ?: 0f
            val steps = etSteps.text.toString().toIntOrNull() ?: 0
            val learnt = etLearnt.text.toString()
            val rating = rbRating.rating

            if (learnt.isEmpty()) {
                Toast.makeText(context, "Please enter what you learnt!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = requireActivity().getSharedPreferences("TrackifyPrefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("today_date", currentDate)
                putFloat("today_study", study)
                putInt("today_water", water)
                putFloat("today_sleep", sleep)
                putInt("today_steps", steps)
                putString("today_learnt", learnt)
                putFloat("today_rating", rating)
                apply()
            }

            findNavController().navigate(R.id.action_inputFragment_to_summaryFragment)
        }
    }
}