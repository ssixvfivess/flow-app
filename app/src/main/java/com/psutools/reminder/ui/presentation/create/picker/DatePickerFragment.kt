package com.psutools.reminder.ui.presentation.create.picker

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.psutools.reminder.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class DatePickerFragment : DialogFragment() {
    private var listener: OnDateSetListener? = null
    private var minDate: Long = 0

    interface OnDateSetListener {
        fun onDateSet(year: Int, month: Int, dayOfMonth: Int)
    }

    fun setListener(listener: OnDateSetListener) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext()).apply {
            setContentView(R.layout.date_picker)

            val datePicker = findViewById<DatePicker>(R.id.datePicker)
            val saveButton = findViewById<Button>(R.id.button_save)

            datePicker.minDate = minDate

            arguments?.getString(INITIAL_DATE_KEY)?.let { dateStr ->
                if (dateStr.isNotEmpty()) {
                    val (year, month, day) = parseDate(dateStr)
                    datePicker.updateDate(year, month - 1, day)
                }
            }

            saveButton.setOnClickListener {
                listener?.onDateSet(
                    datePicker.year,
                    datePicker.month + 1,
                    datePicker.dayOfMonth
                )
                dismiss()
            }
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)

        return dialog
    }

    companion object {
        private const val INITIAL_DATE_KEY = "initial_date"
        private const val MIN_DATE_KEY = "min_date"

        fun newInstance(
            initialDate: String = "",
            minDate: Long = 0,
            listener: OnDateSetListener
        ): DatePickerFragment {
            return DatePickerFragment().apply {
                arguments = Bundle().apply {
                    putString(INITIAL_DATE_KEY, initialDate)
                    putLong(MIN_DATE_KEY, minDate)
                }
                setListener(listener)
                this.minDate = minDate
            }
        }
    }

    private fun parseDate(dateStr: String): Triple<Int, Int, Int> {
        return if (dateStr.isNotEmpty()) {
            try {
                val formatter = SimpleDateFormat("d MMM yyyy 'г.'", Locale("ru"))
                val date = formatter.parse(dateStr)
                val calendar = Calendar.getInstance().apply { time = date!! }
                Triple(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH))
            } catch (e: Exception) {
                getCurrentDate()
            }
        } else {
            getCurrentDate()
        }
    }

    private fun getCurrentDate(): Triple<Int, Int, Int> {
        val calendar = Calendar.getInstance()
        return Triple(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH))
    }
}