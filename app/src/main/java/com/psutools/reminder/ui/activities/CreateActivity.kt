package com.psutools.reminder.ui.activities

import android.content.Context
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
import com.psutools.reminder.utils.ui.collectWithLifecycle
import com.psutools.reminder.utils.ui.isDarkMode
import com.psutools.reminder.utils.ui.tools.switcher.ContentStateSwitcher
import com.psutools.reminder.utils.ui.tools.switcher.base.ContentState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateActivity : BaseActivity<ActivityCreateBinding>() {

    override val viewBinding: ActivityCreateBinding by lazyUnsafe {
        ActivityCreateBinding.inflate(layoutInflater)
    }
    private val viewModel: CreateTripViewModel by viewModels<CreateTripViewModel>()
    private lateinit var contentStateSwitcher: ContentStateSwitcher<ContentState>
    private lateinit var adapter: CreateTripAdapter

    override fun initUi() {
        setupToolbar()
        setupRecycler()

        window?.statusBarColor = ContextCompat.getColor(this, R.color.psu_widget_gray)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = window.decorView
            if (!isDarkMode(this)) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decorView.setSystemUiVisibility(0);
            }
        }
    }

    override fun observeData() {
        viewModel.state.collectWithLifecycle(this) { state ->
            when (state) {
                ScreenState.Loading -> showLoading()
                is ScreenState.Content -> {
                    navigateToNextScreen(state.data)
                }
            }
        }
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

    private fun setupRecycler() {
        adapter = CreateTripAdapter()
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerView.adapter = adapter
    }

    private fun showLoading() {
        contentStateSwitcher.switchState(ContentState.LOADING)
    }

    private fun hideKeyboard(view: View) {
        val hide = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        hide.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun navigateToNextScreen(tripState: CreateTripState) {
//        val intent = Intent(this, EndCreateActivity::class.java).apply {
//            putExtra("TRIP_DATA", viewModel.getTripData())
//        }
//        startActivity(intent)
    }

//    companion object {
//        fun createIntent(context: Context, tripData: CreateTripRequest): Intent {
//            return Intent(context, EndCreateActivity::class.java).apply {
//                putExtra("TRIP_DATA", tripData)
//            }
//        }
//    }
}

//    Когда НУЖНО писать createIntent() в Activity:
//    1. Если Activity ПРИНИМАЕТ данные через Intent (через putExtra)
//    2. Если Activity ЗАПУСКАЕТСЯ из нескольких мест (даже без параметров)
//
//    Когда МОЖНО НЕ ПИСАТЬ createIntent():
//    1. Если Activity НЕ ПРИНИМАЕТ данные и запускается ТОЛЬКО из одного места
//    2. Если Activity ТОЛЬКО ПЕРЕДАЁТ данные (но не принимает), createIntent() ей не нужен