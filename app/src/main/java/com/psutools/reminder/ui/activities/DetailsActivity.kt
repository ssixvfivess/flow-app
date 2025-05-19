package com.psutools.reminder.ui.sample.details

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.psutools.reminder.R
import com.psutools.reminder.base.arch.BaseActivity
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.lazyUnsafe
import com.psutools.reminder.databinding.ActivityDetailsBinding
import com.psutools.reminder.databinding.DeleteWindowBinding
import com.psutools.reminder.ui.presentation.details.TripDataDetailsState
import com.psutools.reminder.ui.presentation.details.TripDataDetailsViewModel
import com.psutools.reminder.ui.presentation.details.adapter.TripDataDetailsAdapter
import com.psutools.reminder.utils.ui.collectWithLifecycle
import com.psutools.reminder.utils.ui.isDarkMode
import com.psutools.reminder.utils.ui.tools.switcher.ContentStateSwitcher
import com.psutools.reminder.utils.ui.tools.switcher.base.ContentState
import com.psutools.reminder.utils.ui.tools.switcher.createContentStateSwitcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {

    override val viewBinding: ActivityDetailsBinding by lazyUnsafe {
        ActivityDetailsBinding.inflate(layoutInflater)
    }
    private val viewModel: TripDataDetailsViewModel by viewModels<TripDataDetailsViewModel>()
    private lateinit var contentStateSwitcher: ContentStateSwitcher<ContentState>
    private lateinit var adapter: TripDataDetailsAdapter


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
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decorView.setSystemUiVisibility(0);
            }
        }
    }

    override fun observeData() {
        viewModel.state.collectWithLifecycle(this) { state ->
            when (state) {
                is ScreenState.Content -> {
                    updateToolbarTitle(state.data.routeName)
                    showContent(state.data)
                }
                ScreenState.Loading -> showLoading()
            }
        }
    }

    private fun setupToolbar() {
        viewBinding.toolbar.backButton.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        viewBinding.toolbar.deleteButton.setOnClickListener {
            showDeleteDialog()
        }
    }

    private fun updateToolbarTitle(title: String) {
        viewBinding.toolbar.titleText.text = title
    }


    private fun setupRecycler() {
        adapter = TripDataDetailsAdapter()
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

    private fun showContent(stateData: TripDataDetailsState) {
        adapter.items = stateData.items
        contentStateSwitcher.switchState(ContentState.CONTENT)
    }

    private fun showLoading() {
        contentStateSwitcher.switchState(ContentState.LOADING)
    }


    private fun showDeleteDialog() {
        val dialogViewBinding = DeleteWindowBinding.inflate(LayoutInflater.from(this), null, false)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogViewBinding.root)
            .create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tripId = intent.getStringExtra("tripId") ?: ""

        with(dialogViewBinding) {
            textForWindow1.setText(R.string.text1_delete_window)
            textForWindow2.setText(R.string.text2_delete_window)

            buttonCancel.setOnClickListener {
                dialog.dismiss()
            }

            buttonDelete.setOnClickListener {
                viewModel.deleteTrip(tripId)
                setResult(
                    Activity.RESULT_OK,
                    Intent().putExtra("DELETED_TRIP_ID", tripId)
                )
                finish()
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, DetailsActivity::class.java)
        }
    }
}
