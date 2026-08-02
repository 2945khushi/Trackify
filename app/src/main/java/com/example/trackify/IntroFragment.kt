package com.example.trackify

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class IntroFragment : Fragment(R.layout.fragment_intro) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        view.findViewById<TextView>(R.id.btn_next).setOnClickListener {
            val sharedPref = requireActivity().getSharedPreferences("TrackifyPrefs", Context.MODE_PRIVATE)
            val userName = sharedPref.getString("user_name", null)

            if (userName == null) {
                // First time user: go to Details/Setup page
                findNavController().navigate(R.id.action_introFragment_to_detailsFragment)
            } else {
                // Returning user: skip setup, go directly to Daily Input
                findNavController().navigate(R.id.action_introFragment_to_inputFragment)
            }
        }
    }
}