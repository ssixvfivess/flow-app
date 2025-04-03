package com.psutools.reminder.ui.fragments

import android.icu.util.Calendar
import android.os.Handler
import android.os.Looper
import android.widget.CalendarView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.psutools.reminder.base.arch.BaseFragment
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.databinding.FragmentRoutesBinding
import com.psutools.reminder.ui.presentation.list.trip.TripDataListState
import com.psutools.reminder.ui.presentation.routes.RoutesDataListAdapter
import com.psutools.reminder.ui.presentation.routes.RoutesDataListState
import com.psutools.reminder.ui.presentation.routes.RoutesDataListViewModel
import com.psutools.reminder.utils.ui.SnackbarManager
import com.psutools.reminder.utils.ui.collectWithLifecycle
import com.psutools.reminder.utils.ui.tools.switcher.ContentStateSwitcher
import com.psutools.reminder.utils.ui.tools.switcher.base.ContentState
import com.psutools.reminder.utils.ui.tools.switcher.createContentStateSwitcher
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.DateTime

@AndroidEntryPoint
class RoutesFragment : BaseFragment<FragmentRoutesBinding>() {

    override fun createViewBinding(): FragmentRoutesBinding {
        return FragmentRoutesBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
        setCalendarToCurrentDate()
    }

    private fun setCalendarToCurrentDate() {
        val today = Calendar.getInstance()
        viewBinding.calendarView.date = today.timeInMillis
    }

    private val viewModel: RoutesDataListViewModel by viewModels<RoutesDataListViewModel>()
    private lateinit var routesDataListAdapter: RoutesDataListAdapter

    private lateinit var contentStateSwitcher: ContentStateSwitcher<ContentState>

    override fun initUi() {
        val isFirstLaunch = viewModel.hasContent

        setupRecycler()
        setupStateSwitcher(isFirstLaunch)
        setupCalendar()

        if (!isFirstLaunch) {
            viewModel.loadData()
        }
    }

    private fun setupCalendar() {
        viewBinding.calendarView.setOnDateChangeListener { _: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            val selectedDate = DateTime()
                .withYear(year)
                .withMonthOfYear(month + 1)
                .withDayOfMonth(dayOfMonth)
                .withTimeAtStartOfDay()

            viewModel.setSelectedDate(selectedDate)
        }
    }

    override fun observeData() {
        viewModel.state.collectWithLifecycle(this) { state ->
            when (state) {
                is ScreenState.Content -> showContent(state.data)
                ScreenState.Loading -> showLoading()
            }
        }
    }

    private fun setupRecycler() {
        routesDataListAdapter = RoutesDataListAdapter(
            onClickListener = onClickListener
        )

        viewBinding.contentRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewBinding.contentRecycler.adapter = routesDataListAdapter
    }

    private fun setupStateSwitcher(isFirstLaunch: Boolean) {
        contentStateSwitcher = createContentStateSwitcher(
            initialState = if (isFirstLaunch) ContentState.LOADING else ContentState.CONTENT,
            mapper = { state ->
                when (state) {
                    ContentState.CONTENT -> listOf(viewBinding.contentRecycler)
                    ContentState.LOADING -> listOf(viewBinding.progress)
                    else -> emptyList()
                }
            }
        )
    }

    private fun showContent(stateData: RoutesDataListState) {
        Handler(Looper.getMainLooper()).post {
            routesDataListAdapter.items = stateData.items
            contentStateSwitcher.switchState(ContentState.CONTENT)
        }
    }

    private fun showLoading() {
        contentStateSwitcher.switchState(ContentState.LOADING)
    }

    private val onClickListener = { text: String ->
        SnackbarManager.createSnackbar(viewBinding.root, "Item: $text")
    }
}