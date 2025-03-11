package com.psutools.reminder.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.psutools.reminder.base.arch.BaseFragment
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.databinding.FragmentHomeBinding
import com.psutools.reminder.ui.sample.list.SampleDataListActivity
import com.psutools.reminder.ui.sample.list.SampleDataListState
import com.psutools.reminder.ui.sample.list.SampleDataListViewModel
import com.psutools.reminder.ui.sample.list.adapter.SampleDataListAdapter
import com.psutools.reminder.utils.ui.SnackbarManager
import com.psutools.reminder.utils.ui.collectWithLifecycle
import com.psutools.reminder.utils.ui.tools.switcher.ContentStateSwitcher
import com.psutools.reminder.utils.ui.tools.switcher.base.ContentState
import com.psutools.reminder.utils.ui.tools.switcher.createContentStateSwitcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun createViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: SampleDataListViewModel by viewModels<SampleDataListViewModel>()
    private lateinit var sampleDataListAdapter: SampleDataListAdapter

    private lateinit var contentStateSwitcher: ContentStateSwitcher<ContentState>

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
        viewBinding.toolbar.setNavigationOnClickListener {
            requireActivity().finish()
        }
    }

    private fun setupRecycler() {
        sampleDataListAdapter = SampleDataListAdapter(
            onClickListener = onClickListener
        )

        viewBinding.contentRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewBinding.contentRecycler.adapter = sampleDataListAdapter
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

    private fun showContent(stateData: SampleDataListState) {
        Handler(Looper.getMainLooper()).post {
            sampleDataListAdapter.items = stateData.items
            contentStateSwitcher.switchState(ContentState.CONTENT)
        }
    }

    private fun showLoading() {
        contentStateSwitcher.switchState(ContentState.LOADING)
    }

    private val onClickListener = { text: String ->
        SnackbarManager.createSnackbar(viewBinding.root, "Item: $text")
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, HomeFragment::class.java)
        }
    }
}