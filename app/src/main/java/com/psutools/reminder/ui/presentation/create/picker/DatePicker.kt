//package com.psutools.reminder.ui.presentation.create.picker
//
//import android.app.DatePickerDialog
//import android.os.Bundle
//import android.util.Log
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContextCompat
//import com.psutools.reminder.R
//import java.util.Calendar
//
//class DatePicker : AppCompatActivity() {
//
//    private val TAG = "MainActivity"
//    private lateinit var displayDate: TextView
//    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        displayDate = findViewById(R.id.tvDate)
//
//        displayDate.setOnClickListener {
//            val cal = Calendar.getInstance()
//            val year = cal.get(Calendar.YEAR)
//            val month = cal.get(Calendar.MONTH)
//            val day = cal.get(Calendar.DAY_OF_MONTH)
//
//            val dialog = DatePickerDialog(
//                this@MainActivity,
//                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                dateSetListener,
//                year, month, day
//            )
//            dialog.window?.setBackgroundDrawable(
//                ContextCompat.getDrawable(this, android.R.color.transparent)
//            )
//            dialog.show()
//        }
//
//        dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
//            val adjustedDay = day + 1
//            Log.d(TAG, "onDateSet: dd/mm/yyy: $adjustedDay/$month/$year")
//
//            val date = "$adjustedDay/$month/$year"
//            displayDate.text = date
//        }
//    }
//}