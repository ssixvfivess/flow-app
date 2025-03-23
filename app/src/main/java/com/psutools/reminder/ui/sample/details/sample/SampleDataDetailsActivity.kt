package com.psutools.reminder.ui.sample.details.sample

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.psutools.reminder.base.arch.BaseActivity
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.base.lazyUnsafe
import com.psutools.reminder.databinding.ActivitySampleDataDetailsBinding
import com.psutools.reminder.utils.ui.collectWithLifecycle
import com.psutools.reminder.utils.ui.tools.switcher.ContentStateSwitcher
import com.psutools.reminder.utils.ui.tools.switcher.base.ContentState
import com.psutools.reminder.utils.ui.tools.switcher.createContentStateSwitcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleDataDetailsActivity : BaseActivity<ActivitySampleDataDetailsBinding>() {

    override val viewBinding: ActivitySampleDataDetailsBinding by lazyUnsafe {
        ActivitySampleDataDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel: SampleDataDetailsViewModel by viewModels<SampleDataDetailsViewModel>()

    private lateinit var contentStateSwitcher: ContentStateSwitcher<ContentState>

    override fun initUi() {
        val isFirstLaunch = viewModel.hasContent

        setupToolbar()
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
        viewBinding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupStateSwitcher(isFirstLaunch: Boolean) {
        contentStateSwitcher = createContentStateSwitcher(
            initialState = if (isFirstLaunch) ContentState.LOADING else ContentState.CONTENT,
            mapper = { state ->
                when (state) {
                    ContentState.CONTENT -> listOf(viewBinding.contentText)
                    ContentState.LOADING -> listOf(viewBinding.progress)
                    else -> emptyList()
                }
            }
        )
    }

    private fun showContent(stateData: SampleDataDetailsState) {
        viewBinding.contentText.text = stateData.text
        contentStateSwitcher.switchState(ContentState.CONTENT)
    }

    private fun showLoading() {
        contentStateSwitcher.switchState(ContentState.LOADING)
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, SampleDataDetailsActivity::class.java)
        }
    }
}
