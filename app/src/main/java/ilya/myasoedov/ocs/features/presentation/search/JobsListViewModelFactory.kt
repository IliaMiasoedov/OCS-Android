package ilya.myasoedov.ocs.features.presentation.search

import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ilya.myasoedov.ocs.features.domain.usecases.GetPositionsUseCase
import javax.inject.Inject

class JobsListViewModelFactory @Inject constructor(
    private val useCase: GetPositionsUseCase,
    fragment: SearchFragment
) : AbstractSavedStateViewModelFactory(fragment, null) {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return JobsListViewModel(getPositionsUseCase = useCase, savedStateHandle = handle) as T
    }
}