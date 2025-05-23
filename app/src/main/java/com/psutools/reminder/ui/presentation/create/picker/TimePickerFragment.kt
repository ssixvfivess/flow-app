package com.psutools.reminder.ui.presentation.create.picker

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.psutools.reminder.R

class TimePickerFragment : DialogFragment() {
    private var listener: OnTimeSetListener? = null

    interface OnTimeSetListener {
        fun onTimeSet(hourOfDay: Int, minute: Int)
    }

    fun setListener(listener: OnTimeSetListener) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext()).apply {
            setContentView(R.layout.time_picker)

            val timePicker = findViewById<TimePicker>(R.id.timePicker).apply{
                setIs24HourView(true)
            }
            val saveButton = findViewById<Button>(R.id.button_save)

            arguments?.getString(INITIAL_TIME_KEY)?.let { timeStr ->
                if (timeStr.isNotEmpty()) {
                    val (hours, minutes) = parseTime(timeStr)
                    timePicker.hour = hours
                    timePicker.minute = minutes
                }
            }

            saveButton.setOnClickListener {
                listener?.onTimeSet(timePicker.hour, timePicker.minute)
                dismiss()
            }
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)

        return dialog
    }

    private fun parseTime(timeStr: String): Pair<Int, Int> {
        return try {
            val parts = timeStr.split(":")
            parts[0].toInt() to parts[1].toInt()
        } catch (e: Exception) {
            8 to 0 // Значение по умолчанию 08:00
        }
    }

    companion object {
        private const val INITIAL_TIME_KEY = "initial_time"

        fun newInstance(initialTime: String = "", listener: OnTimeSetListener): TimePickerFragment {
            return TimePickerFragment().apply {
                arguments = Bundle().apply {
                    putString(INITIAL_TIME_KEY, initialTime)
                }
                setListener(listener)
            }
        }
    }
}