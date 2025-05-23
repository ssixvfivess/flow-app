package com.psutools.reminder.ui.presentation.create.adapter.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.CreateDetailsBinding
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreateDetailsDelegate.ViewHolder
import com.psutools.reminder.ui.presentation.create.picker.DatePickerFragment
import com.psutools.reminder.ui.presentation.create.picker.ReminderPickerFragment
import com.psutools.reminder.ui.presentation.create.picker.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateDetailsDelegate(
    private val onTimeSelected: (String) -> Unit,
    private val onDateSelected: (String) -> Unit,
    private val onReminderSelected: (String) -> Unit
) : BaseItemAdapterDelegate<CreateDetails, ViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is CreateDetails

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding = CreateDetailsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBind(item: CreateDetails, holder: ViewHolder, payloads: List<Any>) {
        with(holder.binding) {
            editDate.apply {
                setText(item.selectedDate)
                isFocusable = false
                setOnClickListener {
                    showDatePicker(holder.itemView.context, item.selectedDate)
                }
            }
            editArrivalTime.apply {
                setText(item.selectedTime)
                isFocusable = false
                setOnClickListener {
                    showTimePicker(holder.itemView.context, item.selectedTime)
                }
            }
            editReminder.apply {
                setText(item.selectedReminder)
                isFocusable = false
                setOnClickListener {
                    // Парсим текущее значение напоминания (если есть)
                    val currentMinutes = try {
                        item.selectedReminder.replace(" мин", "").toInt()
                    } catch (e: Exception) {
                        5 // значение по умолчанию
                    }
                    showReminderPicker(holder.itemView.context, currentMinutes)
                }
            }
        }
    }

    private fun showTimePicker(context: Context, currentTime: String) {
        val fragmentManager = (context as? FragmentActivity)?.supportFragmentManager
        fragmentManager?.let {
            TimePickerFragment.newInstance(
                initialTime = currentTime,
                listener = object : TimePickerFragment.OnTimeSetListener {
                    override fun onTimeSet(hourOfDay: Int, minute: Int) {
                        val formattedTime = String.format("%02d:%02d", hourOfDay, minute)
                        onTimeSelected(formattedTime)
                    }
                }
            ).show(it, "timePickerDialog")
        }
    }

    private fun showReminderPicker(context: Context, currentMinutes: Int) {
        val fragmentManager = (context as? FragmentActivity)?.supportFragmentManager
        fragmentManager?.let {
            ReminderPickerFragment.newInstance(
                initialValue = currentMinutes,
                listener = object : ReminderPickerFragment.OnReminderSetListener {
                    override fun onReminderSet(minutes: Int) {
                        // Обработка выбранного значения
                        onReminderSelected("За $minutes минут")
                    }
                }
            ).show(it, "reminderPickerDialog")
        }
    }

    private fun showDatePicker(context: Context, currentDate: String) {
        val fragmentManager = (context as? FragmentActivity)?.supportFragmentManager
        fragmentManager?.let {
            val calendar = Calendar.getInstance()
            val currentYear = calendar.get(Calendar.YEAR)
            val currentMonth = calendar.get(Calendar.MONTH)
            val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerFragment.newInstance(
                initialDate = currentDate,
                minDate = calendar.timeInMillis,
                listener = object : DatePickerFragment.OnDateSetListener {
                    override fun onDateSet(year: Int, month: Int, dayOfMonth: Int) {
                        val selectedCalendar = Calendar.getInstance().apply {
                            set(year, month - 1, dayOfMonth)
                        }

                        if (selectedCalendar.timeInMillis >= calendar.timeInMillis) {
                            val formattedDate = SimpleDateFormat("d MMM yyyy 'г.'", Locale("ru"))
                                .format(selectedCalendar.time)
                                .replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                                    else it.toString()
                                }
                            onDateSelected(formattedDate)
                        } else {
                            Toast.makeText(
                                context,
                                "Нельзя выбрать дату в прошлом",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            ).show(it, "datePickerDialog")
        }
    }

    class ViewHolder(val binding: CreateDetailsBinding) : RecyclerView.ViewHolder(binding.root)
}