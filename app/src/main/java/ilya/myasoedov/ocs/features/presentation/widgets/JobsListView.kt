package ilya.myasoedov.ocs.features.presentation.widgets

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.findFragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import ilya.myasoedov.ocs.R
import ilya.myasoedov.ocs.features.domain.entites.Position
import ilya.myasoedov.ocs.features.presentation.adapter.PositionsAdapter
import ilya.myasoedov.ocs.features.presentation.detail.argPosition
import ilya.myasoedov.ocs.features.presentation.search.JobsListViewModel
import ilya.myasoedov.ocs.features.presentation.search.Progress
import ilya.myasoedov.ocs.features.presentation.search.SearchFragment
import ilya.myasoedov.ocs.providers.ImageLoadProvider

class JobsListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var viewModel: JobsListViewModel
    private lateinit var imageLoadProvider: ImageLoadProvider
    private lateinit var lifecycleOwner: LifecycleOwner

    private lateinit var adapter: PositionsAdapter

    private val viewJobsList: RecyclerView
    private val swipeRefresh: SwipeRefreshLayout

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_jobs_list, this, true)

        viewJobsList = view.findViewById(R.id.view_jobs_list)
        swipeRefresh = view.findViewById(R.id.view_jobs_swipe_refresh)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val fragment = findFragment<SearchFragment>()

        viewModel = fragment.jobsListViewModel
        imageLoadProvider = fragment.imageLoadProvider
        lifecycleOwner = fragment.viewLifecycleOwner

        setupAdapter()
        swipeRefresh.setOnRefreshListener {
            viewModel.invalidate()
        }
        observeModel()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        viewModel.setLastListPosition(
            (viewJobsList.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        )
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        val dividerDecoration = DividerItemDecoration(context, layoutManager.orientation)
        adapter = PositionsAdapter(
            imageLoadProvider = imageLoadProvider,
            onItemClickListener = this::openDetailPage
        )
        viewJobsList.layoutManager = layoutManager
        viewJobsList.adapter = adapter
        viewJobsList.setHasFixedSize(true)
        viewJobsList.addItemDecoration(dividerDecoration)
    }

    private fun observeModel() {
        viewModel.positionsLiveData.observe(lifecycleOwner, Observer { pagedList ->
            adapter.submitList(pagedList)
        })
        viewModel.progressLiveData.observe(lifecycleOwner, Observer { progress ->
            if (progress is Progress.Initial) {
                swipeRefresh.isRefreshing = progress.progress
            } else if (progress is Progress.Pagination) {
                adapter.showProgress(showProgress = progress.progress)
            }
        })
        viewModel.lastListPositionLiveData.observe(lifecycleOwner, Observer { initPosition ->
            viewJobsList.scrollToPosition(initPosition)
        })
        viewModel.errorLiveData.observe(lifecycleOwner, Observer { error ->
            Snackbar.make(this, error.message.toString(), Snackbar.LENGTH_LONG).show()
        })
    }

    private fun openDetailPage(position: Position) {
        val args = Bundle().apply { putParcelable(argPosition, position) }
        Navigation.findNavController(this)
            .navigate(R.id.detail_page, args)
    }
}