package ilya.myasoedov.ocs.features.presentation.search


import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ilya.myasoedov.ocs.features.domain.entites.Position
import ilya.myasoedov.ocs.features.domain.usecases.GetPositionsUseCase
import ilya.myasoedov.ocs.paging.PageDataFactory
import ilya.myasoedov.ocs.paging.PageDataSourceCallback
import ilya.myasoedov.ocs.util.SingleLiveEvent
import javax.inject.Inject

private const val initialLoadSizeHint = 20
private const val pageSize = 30
private const val keyQuery = "key_query"
private const val keyListPos = "key_last_list_position"

class JobsListViewModel @Inject constructor(
    private val getPositionsUseCase: GetPositionsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), PageDataSourceCallback<Position> {

    private val progressState = MutableLiveData<Progress>()
    private val pageDataFactory = PageDataFactory(this, viewModelScope)
    private val errorData = SingleLiveEvent<Throwable>()

    val positionsLiveData: LiveData<PagedList<Position>>
    val progressLiveData: LiveData<Progress>
        get() = progressState
    val lastListPositionLiveData = savedStateHandle.getLiveData<Int>(keyListPos)
    val errorLiveData: LiveData<Throwable>
        get() = errorData

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(initialLoadSizeHint)
            .setPageSize(pageSize)
            .build()

        positionsLiveData = LivePagedListBuilder(pageDataFactory, pagedListConfig).build()
    }

    fun invalidate() {
        pageDataFactory.invalidate()
    }

    fun setQuery(newQuery: String) {
        savedStateHandle.set(keyQuery, newQuery)
        invalidate()
    }

    fun setLastListPosition(position: Int) {
        savedStateHandle.set(keyListPos, position)
    }

    override suspend fun loadInitial(): List<Position> {
        progressState.value = Progress.Initial(true)
        val result = getPositions(0)
        progressState.value = Progress.Initial(false)
        return result
    }

    override suspend fun load(page: Long): List<Position> {
        progressState.value = Progress.Pagination(true)
        val result = getPositions(page)
        progressState.value = Progress.Pagination(false)
        return result
    }

    private suspend fun getPositions(page: Long): List<Position> {
        return getPositionsUseCase
            .getPositions(savedStateHandle.get<String>(keyQuery) ?: "", page)
            .getOrElse {
                errorData.value = it
                listOf()
            }
    }
}

sealed class Progress {
    class Initial(val progress: Boolean) : Progress()
    class Pagination(val progress: Boolean) : Progress()
}