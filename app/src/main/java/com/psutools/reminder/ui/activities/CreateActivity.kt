package com.psutools.reminder.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.psutools.reminder.R
import com.psutools.reminder.base.arch.BaseActivity
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.lazyUnsafe
import com.psutools.reminder.databinding.ActivityCreateBinding
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
//            when (state) {
//                is ScreenState.Content -> {
//                    updateToolbarTitle(state.data.routeName)
//                    showContent(state.data)
//                }
//                ScreenState.Loading -> showLoading()
//            }
        }
    }

    private fun setupToolbar() {
        viewBinding.toolbar.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        viewBinding.toolbar.titleText.apply {
            hint = "Название поездки"
            isFocusable = true
            isFocusableInTouchMode = true
            isCursorVisible = true
        }
    }


    private fun setupRecycler() {
        adapter = CreateTripAdapter()
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerView.adapter = adapter
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, CreateActivity::class.java)
        }
    }
}