package com.psutools.reminder.ui.presentation.create.picker

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.psutools.reminder.R

class ReminderPickerFragment : DialogFragment() {
    private var listener: OnReminderSetListener? = null

    interface OnReminderSetListener {
        fun onReminderSet(minutes: Int)
    }

    fun setListener(listener: OnReminderSetListener) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext()).apply {
            setContentView(R.layout.remider_picker)

            val numberPicker = findViewById<NumberPicker>(R.id.reminder_picker)
            val saveButton = findViewById<Button>(R.id.button_save)

            val values = (5..100 step 5).toList().toTypedArray()

            numberPicker.apply {
                minValue = 0
                maxValue = values.size - 1
                displayedValues = values.map { "$it мин" }.toTypedArray()
                wrapSelectorWheel = false

                arguments?.getInt(INITIAL_VALUE_KEY)?.let { initialValue ->
                    val index = values.indexOfFirst { it == initialValue }
                    if (index != -1) value = index
                }
            }

            saveButton.setOnClickListener {
                val selectedMinutes = values[numberPicker.value]
                listener?.onReminderSet(selectedMinutes)
                dismiss()
            }
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)

        return dialog
    }

    companion object {
        private const val INITIAL_VALUE_KEY = "initial_value"

        fun newInstance(
            initialValue: Int = 15,
            listener: OnReminderSetListener
        ): ReminderPickerFragment {
            return ReminderPickerFragment().apply {
                arguments = Bundle().apply {
                    putInt(INITIAL_VALUE_KEY, initialValue)
                }
                setListener(listener)
            }
        }
    }
}