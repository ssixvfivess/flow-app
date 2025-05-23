package com.psutools.reminder.ui.activities

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.psutools.reminder.R
import com.psutools.reminder.base.arch.BaseActivity
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.lazyUnsafe
import com.psutools.reminder.databinding.ActivityCreateBinding
import com.psutools.reminder.ui.presentation.create.CreateTripState
import com.psutools.reminder.ui.presentation.create.CreateTripViewModel
import com.psutools.reminder.ui.presentation.create.adapter.CreateTripAdapter
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreateDetails
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreatePointA
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreatePointB
import com.psutools.reminder.utils.ui.collectWithLifecycle
import com.psutools.reminder.utils.ui.isDarkMode
import com.psutools.reminder.utils.ui.tools.switcher.ContentStateSwitcher
import com.psutools.reminder.utils.ui.tools.switcher.base.ContentState
import com.psutools.reminder.utils.ui.tools.switcher.createContentStateSwitcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateActivity : BaseActivity<ActivityCreateBinding>() {

    override val viewBinding: ActivityCreateBinding by lazyUnsafe {
        ActivityCreateBinding.inflate(layoutInflater)
    }
    private val viewModel: CreateTripViewModel by viewModels()
    private lateinit var contentStateSwitcher: ContentStateSwitcher<ContentState>
    private lateinit var adapter: CreateTripAdapter

    override fun initUi() {

        val isFirstLaunch = viewModel.hasContent

        setupToolbar()
        setupRecycler()
        setupStateSwitcher(isFirstLaunch)


        if (!isFirstLaunch) {
            viewModel.loadData()
        }

        window?.statusBarColor = ContextCompat.getColor(this, R.color.psu_widget_gray)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = window.decorView
            if (!isDarkMode(this)) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = 0
            }
        }
    }

    override fun observeData() {
        viewModel.state.collectWithLifecycle(this) { state ->
            when (state) {
                is ScreenState.Content -> {
                    showContent(state.data)
                }

                ScreenState.Loading -> showLoading()
            }
        }
    }

    private fun showLoading() {
        contentStateSwitcher.switchState(ContentState.LOADING)
    }

    private fun setupToolbar() {
        viewBinding.toolbar.apply {

            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            titleText.apply {
                hint = "Название поездки"
                isFocusable = true
                isFocusableInTouchMode = true
                isCursorVisible = true
            }

            root.setOnClickListener {
                titleText.clearFocus()
                hideKeyboard(titleText)
            }
        }
    }

    private fun showContent(stateData: CreateTripState) {
        when (stateData) {
            is CreateTripState -> {
                //список элементов для адаптера
                val items = listOf(
                    CreatePointA(hint = stateData.routeAHint, point = stateData.pointA),
                    CreatePointB(hint = stateData.routeBHint, point = stateData.pointB),
                    CreateDetails(
                        selectedDate = stateData.selectedDate,
                        selectedTime = stateData.selectedTime,
                        selectedReminder = stateData.selectedReminder
                    )
                )
                adapter.items = items //отдаем элементы в адаптер

                var isFormValid: Boolean =
                    false // todo кнопка "Далее" неактивна, позже прописать логику
                viewBinding.nextButton.isEnabled = isFormValid
                viewBinding.nextButton.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this,
                        if (isFormValid) R.color.psu_yellow else R.color.psu_not_active
                    )
                )

                contentStateSwitcher.switchState(ContentState.CONTENT)
            }
        }
    }

    private fun hideKeyboard(view: View) {
        val hide = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        hide.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setupRecycler() {
        adapter = CreateTripAdapter(
            onTimeSelected = { selectedTime ->
                viewModel.updateSelectedTime(selectedTime)
            },
            onDateSelected = { selectedDate ->
                viewModel.updateSelectedDate(selectedDate)
            },
            onReminderSelected = {selectedReminder ->
                viewModel.updateSelectedReminder(selectedReminder)
            }
        )
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerView.adapter = adapter
    }

    private fun setupStateSwitcher(isFirstLaunch: Boolean) {
        contentStateSwitcher = createContentStateSwitcher(
            initialState = if (isFirstLaunch) ContentState.LOADING else ContentState.CONTENT,
            mapper = { state ->
                when (state) {
                    ContentState.CONTENT -> listOf(viewBinding.recyclerView)
                    else -> emptyList()
                }
            }
        )
    }
}