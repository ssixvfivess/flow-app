package com.psutools.reminder.ui.fragments

import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.psutools.reminder.app.navigation.Router
import com.psutools.reminder.base.arch.BaseFragment
import com.psutools.reminder.base.arch.ScreenState
import com.psutools.reminder.databinding.FragmentHomeBinding
import com.psutools.reminder.ui.presentation.list.trip.TripDataListState
import com.psutools.reminder.ui.presentation.list.trip.TripDataListViewModel
import com.psutools.reminder.ui.presentation.list.adapter.TripDataListAdapter
import com.psutools.reminder.utils.ui.collectWithLifecycle
import com.psutools.reminder.utils.ui.tools.switcher.ContentStateSwitcher
import com.psutools.reminder.utils.ui.tools.switcher.base.ContentState
import com.psutools.reminder.utils.ui.tools.switcher.createContentStateSwitcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject
    lateinit var router: Router

    override fun createViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: TripDataListViewModel by viewModels<TripDataListViewModel>()
    private lateinit var tripDataListAdapter: TripDataListAdapter

    private lateinit var contentStateSwitcher: ContentStateSwitcher<ContentState>

    override fun initUi() {
        val isFirstLaunch = viewModel.hasContent

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

    private fun setupRecycler() {
        tripDataListAdapter = TripDataListAdapter(
            onClickListener = onClickListener
        )

        viewBinding.contentRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewBinding.contentRecycler.adapter = tripDataListAdapter
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

    private fun showContent(stateData: TripDataListState) {
            tripDataListAdapter.items = stateData.items
            contentStateSwitcher.switchState(ContentState.CONTENT)
    }

    private fun showLoading() {
        contentStateSwitcher.switchState(ContentState.LOADING)
    }

    private val deleteResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewBinding.contentRecycler.postDelayed({
                viewModel.loadData()
            }, 300)
        }
    }

    private val onClickListener = { tripId: String ->
        val intent = router.createRouteDetailsIntent(requireContext(), tripId)
        deleteResultLauncher.launch(intent)
    }
}