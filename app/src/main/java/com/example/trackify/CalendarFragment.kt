package com.example.trackify

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.util.Calendar

class CalendarFragment : Fragment(R.layout.fragment_calendar) {
    private var selectedDate: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendarView = view.findViewById<CalendarView>(R.id.calendar_view)
        val btnViewSummary = view.findViewById<Button>(R.id.btn_view_date_summary)

        // Set default date to today
        val calendar = Calendar.getInstance()
        selectedDate = "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        }

        btnViewSummary.setOnClickListener {
            // In a real app, you'd pass this date to SummaryFragment via Bundle
            // For now, we just navigate there
            findNavController().navigate(R.id.summaryFragment)
        }
    }
}