package ilya.myasoedov.ocs.paging

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PageDataSource<T>(
    private var pageDataSourceCallback: PageDataSourceCallback<T>?,
    private var scope: CoroutineScope?
) : PageKeyedDataSource<Long, T>() {

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, T>) = Unit

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, T>
    ) {
        scope?.launch {
            pageDataSourceCallback?.apply {
                callback.onResult(loadInitial(), null, 1L)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, T>) {
        scope?.launch {
            pageDataSourceCallback?.apply {
                val result = load(params.key)
                callback.onResult(result, if (result.isEmpty()) null else params.key + 1)
            }
        }
    }

    fun clearRes() {
        pageDataSourceCallback = null
        scope = null
    }
}

interface PageDataSourceCallback<T> {
    suspend fun loadInitial(): List<T>
    suspend fun load(page: Long): List<T>
}