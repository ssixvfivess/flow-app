package com.psutools.reminder.ui.sample.details

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.psutools.reminder.R
import com.psutools.reminder.base.arch.BaseActivity
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.lazyUnsafe
import com.psutools.reminder.databinding.ActivityDetailsBinding
import com.psutools.reminder.ui.presentation.details.TripDataDetailsState
import com.psutools.reminder.ui.presentation.details.TripDataDetailsViewModel
import com.psutools.reminder.ui.presentation.details.adapter.TripDataDetailsAdapter
import com.psutools.reminder.utils.ui.collectWithLifecycle
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
    }

    override fun observeData() {
        viewModel.state.collectWithLifecycle(this) { state ->
            when (state) {
                is ScreenState.Content -> showContent(state.data)
                ScreenState.Loading -> showLoading()
            }
        }
    }

    private fun setupToolbar() {
        (viewBinding.toolbar as? ViewGroup)?.let { toolbar ->
            toolbar.findViewById<View>(R.id.back_button)?.setOnClickListener {
                finish()
            }
        }
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

//    private val onClickListener = { text: String ->
//        SnackbarManager.createSnackbar(viewBinding.root, text)
//    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, DetailsActivity::class.java)
        }
    }
}