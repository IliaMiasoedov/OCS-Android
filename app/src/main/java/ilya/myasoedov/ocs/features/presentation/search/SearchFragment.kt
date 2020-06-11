package ilya.myasoedov.ocs.features.presentation.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ilya.myasoedov.ocs.R
import ilya.myasoedov.ocs.app.BaseFragment
import ilya.myasoedov.ocs.features.presentation.widgets.SearchView
import ilya.myasoedov.ocs.providers.ImageLoadProvider
import javax.inject.Inject

class SearchFragment : BaseFragment<SearchFragmentViewModel>() {

    @Inject
    lateinit var jobsListViewModelFactory: JobsListViewModelFactory
    val jobsListViewModel by viewModels<JobsListViewModel> { jobsListViewModelFactory }

    @Inject
    lateinit var imageLoadProvider: ImageLoadProvider

    override fun layoutRes(): Int = R.layout.fragment_search
}